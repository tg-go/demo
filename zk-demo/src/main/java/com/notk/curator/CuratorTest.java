package com.notk.curator;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bo.luo
 * @date 2020/12/2 9:43
 */
public class CuratorTest {

    private CuratorFramework curatorFramework;

    @Before
    public void before() {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .retryPolicy(new RetryOneTime(1000)) // 重试策略
                .namespace("zfpt")  //命令空间 chroot
                .build();

        curatorFramework.start();
    }

    @Test
    public void create() throws Exception {
        // 创建一个持久节点，内容为空
        curatorFramework.create().forPath("/dus");

        // 创建一个持久节点，内容不为空
        curatorFramework.create().forPath("/dus1", "test".getBytes());

        // 创建一个临时节点，内容为空  CreateMode标识节点类型
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/dus2");

        // 创建一个临时节点，并递归创建不存在的父节点
        // zk中规定所有非叶子节点必须为持久节点，所以最后一级目录才是临时节点
        curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/dj/dus2");

    }

    @Test
    public void delete() throws Exception {

        // 删除一个节点
        curatorFramework.delete().forPath("/dus");

        // 删除一个节点，并递归删除其所有子节点
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/dus");

        // 删除一个节点，强制指定版本进行删除
        curatorFramework.delete().withVersion(1).forPath("/dus");

        // 删除一个几点，强制保证删除成功
        // guaranteed在删除失败后，后台会持续进行删除操作
        curatorFramework.delete().guaranteed().forPath("/dus");
    }

    @Test
    public void read() throws Exception {
        String path = "";
        curatorFramework.getData().forPath(path);

        Stat stat = null;
        // 读取数据之外，并读取stat
        curatorFramework.getData().storingStatIn(stat).forPath(path);
    }


    @Test
    public void update() throws Exception {
        String path = "";
        curatorFramework.setData().forPath(path);

        // 更新一个节点的内容，强制版本进行更新
        curatorFramework.setData().withVersion(1).forPath(path);
    }


    /**
     * 异步接口
     * 没有传入自定义线程池，则由EventThread这个线程串行处理。
     * 如果传入了，则由自定义线程池去处理
     *
     * @throws Exception
     */
    @Test
    public void BackgroundCallBackTest() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        curatorFramework.getData().inBackground((client, event) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event);
            System.out.println(client);
        }).forPath("/trade");

        Executor executor = Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("curator-%d").build());

        curatorFramework.getData().inBackground((client, event) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(event);
            System.out.println(client);
        }, executor).forPath("/trade");

        countDownLatch.await();
    }

    @Test
    public void nodeCacheTest() throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/trade", false);
        // 是否启动的时候就到Zk上获取节点数据
        nodeCache.start(false);

        nodeCache.getListenable().addListener(() -> {
            System.out.println("data update");
        });
    }


    @Test
    public void masterSelect() throws Exception {
        AtomicInteger masterCount = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, "/master_selector", new LeaderSelectorListenerAdapter() {
                    @Override
                    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                        masterCount.incrementAndGet();
                        System.out.println("become master");
                        Thread.sleep(1000L);
                        System.out.println("宕机失去" + masterCount.decrementAndGet());
                    }
                });
                leaderSelector.autoRequeue();
                leaderSelector.start();
            });
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void disLock() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/trade/mylock");

        for (int i = 0; i < 100; i++) {
            Thread currentThread = new Thread(() -> {
                try{
                    lock.acquire();
                    System.out.println("获取锁");
                }catch (Exception e){

                }finally {
                    System.out.println("释放锁");
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
