package com.nnynn.thread;

public class WaitNotifyDemo {

    static final Object lock = new Object();

    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Produce());
        Thread t2 = new Thread(new Consume());
        t2.start();
        Thread.sleep(1000);
        t1.start();

        t1.join();
        t2.join();

    }

    static class Produce implements Runnable{

        public void run() {
            synchronized (lock){
                System.out.println("进入生产者线程");
                flag=true;
                try {
                    lock.notify();
                    Thread.sleep(10000);
                    System.out.println("退出生产者线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consume implements Runnable{

        public void run() {
            synchronized (lock){
                System.out.println("进入消费者线程");
                try {
                    lock.wait();
                    flag=false;
                    System.out.println("结束等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
