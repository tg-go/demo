package com.notk.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeper_Construct_Usage_Simple implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        // 这里的客户端创建完毕之后，就直接返回了
        // 后续成功建立连接之后，会回调WatcherEvent
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new ZooKeeper_Construct_Usage_Simple());

        System.out.println(zooKeeper.getState());

        try{
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("Zk Session success");
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive:"+watchedEvent);
        if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }
}
