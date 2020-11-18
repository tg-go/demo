package com.notk.zookeeper;

import com.notk.conf.ZkConf;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class ZooKeeper_Create_Async implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ZkConf.ZK_ADDRESS, 5000, new ZooKeeper_Create_Async());

        countDownLatch.await();


        zooKeeper.create(
                "/zk-test",
                "".getBytes(),
                OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT,
                new IStringCallback(),
                "I am context"
        );


        zooKeeper.create(
                "/zk-test",
                "".getBytes(),
                OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(),
                "I am context2");


        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}

class IStringCallback implements AsyncCallback.StringCallback{

    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("Create path result:["+rc+","+path+","+ctx+",real path name:"+name);
    }
}
