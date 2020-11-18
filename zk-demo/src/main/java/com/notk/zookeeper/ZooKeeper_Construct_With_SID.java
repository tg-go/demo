package com.notk.zookeeper;

import com.notk.conf.ZkConf;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeper_Construct_With_SID implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(ZkConf.ZK_ADDRESS,5000,new ZooKeeper_Construct_With_SID());
        System.out.println("sessionId:"+zooKeeper.getSessionId());
        countDownLatch.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();

        System.out.println("sessionId:"+sessionId);
        System.out.println("passwd:"+String.valueOf(sessionPasswd));

        zooKeeper = new ZooKeeper(ZkConf.ZK_ADDRESS,5000,new ZooKeeper_Construct_With_SID()
        ,1L,"test".getBytes());

        zooKeeper = new ZooKeeper(ZkConf.ZK_ADDRESS,5000,new ZooKeeper_Construct_With_SID(),
                sessionId,sessionPasswd);

        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("Event:"+watchedEvent);
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }

    }
}
