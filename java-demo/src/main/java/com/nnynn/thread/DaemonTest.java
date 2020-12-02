package com.nnynn.thread;

import org.junit.Test;

/**
 * @author bo.luo
 * @date 2020/12/2 16:23
 */
public class DaemonTest {

    @Test
    public void testDaemon() throws InterruptedException {
        Thread mainThread = new Thread(new Runnable() {
            public void run() {
                Thread childThread = new Thread(new Runnable() {
                    public void run() {
                        while (true) {
                            System.out.println("I am Children");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                childThread.setDaemon(true);
                childThread.start();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Main Thread finish");
            }
        });
        mainThread.start();
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testDeamon2() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("start");
                }
            }
        });
        thread.start();
        thread.setDaemon(true);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
