package com.nnynn.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author bo.luo
 * @date 2020/12/3 10:57
 */
public class UncaughtExceptionTest {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Thread thread = new Thread(new MyTask());
        //thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        exec.execute(thread);

        Future  future = exec.submit(new MyTask());
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(111);
            e.printStackTrace();
        }
    }
}

class MyTask implements Runnable{

    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println(1/0);
    }
}
class MyTask2 implements Runnable{

    public void run() {
        System.out.println(1/0);
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught error,msg="+e.getMessage());
    }
}
