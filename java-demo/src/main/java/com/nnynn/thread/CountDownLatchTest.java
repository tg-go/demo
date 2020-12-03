package com.nnynn.thread;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch:允许一组线程互相等待，直到到达某个公共屏障点，才会进行后续任务
 * 计数无法被重置
 *
 * @author bo.luo
 * @date 2020/12/3 16:46
 */
public class CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    static class BossThread extends Thread {

        @Override
        public void run() {
            System.out.println("Boss wait," + countDownLatch.getCount() + "开会");
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("所有人到齐，开会....");
        }
    }

    static class EmployeeThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "到达会议室");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        new BossThread().start();

        for (int i = 0; i < 5; i++) {
            new EmployeeThread().start();
        }
    }

}



