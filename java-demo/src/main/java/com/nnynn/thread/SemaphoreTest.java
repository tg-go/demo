package com.nnynn.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author bo.luo
 * @date 2020/12/3 16:35
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        // 公平锁
        final Semaphore semp = new Semaphore(5, true);

        for (int index = 0; index < 10; index++) {
            final int no = index;
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        semp.acquire();
                        System.out.println("Access :" + no);
                        Thread.sleep(1000);
                        semp.release();
                        System.out.println("available:" + semp.availablePermits() + ",wait:" + semp.hasQueuedThreads());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(runnable);
        }
        exec.shutdown();
    }
}
