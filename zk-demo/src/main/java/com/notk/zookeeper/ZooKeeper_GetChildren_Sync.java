package com.notk.zookeeper;

import com.notk.conf.ZkConf;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeper_GetChildren_Sync implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper(ZkConf.ZK_ADDRESS, 5000, new ZooKeeper_GetChildren_Sync());

        countDownLatch.await();

    }

    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
