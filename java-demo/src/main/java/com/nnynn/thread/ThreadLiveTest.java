package com.nnynn.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author bo.luo
 * @date 2020/12/3 16:24
 */
public class ThreadLiveTest {

    @Test
    public void test1(){
        Thread thread = new Thread();
        thread.start();
    }

    public static void main(String[] args) {
        Executors.newFixedThreadPool(3);
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        ExecutorService service = Executors.newScheduledThreadPool(3);
        service.execute(new Runnable() {
            public void run() {

            }
        });
    }
}
