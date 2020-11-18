package com.notk.zookeeper;

import com.notk.conf.ZkConf;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeper_Create_Sync implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ZkConf.ZK_ADDRESS, 5000, new ZooKeeper_Create_Sync());

        countDownLatch.await();

        String path1 = zooKeeper.create("/zk-test", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE
                , CreateMode.PERSISTENT);

        System.out.println("Create Success:" + path1);

        String path2 = zooKeeper.create("/zk-test", "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println("create Success:" + path2);
    }

    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
