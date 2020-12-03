package com.nnynn.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author bo.luo
 * @date 2020/12/3 19:42
 */
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier;

    static class CyclicBarrierThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到了");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("开会吧");
            }
        });

        for (int i = 0; i < 5; i++) {
            new CyclicBarrierThread().start();
        }
    }
}
