package com.nnynn.thread;

/**
 * @author bo.luo
 * @date 2020/12/2 16:30
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("主线程开始");
                Thread sonThread = new Thread(new Thread1(Thread.currentThread()));
                sonThread.setDaemon(true);
                sonThread.start();

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("主线程结束");
            }
        });

        mainThread.start();
    }
}

class Thread1 implements Runnable{
    private Thread mianThread;

    public Thread1(Thread mianThread) {
        this.mianThread = mianThread;
    }

    public void run() {
        while (mianThread.isAlive()){
            System.out.println("子线程正在运行....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


