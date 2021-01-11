package com.nnynn.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bo.luo
 * @date 2021/1/11 19:39
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5;i++){
            service.submit(new Producer(list,8));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(list));
        }
    }

}


class Producer implements Runnable {

    private List<Integer> list;

    private int maxLength;

    public Producer(List<Integer> list, int maxLength) {
        this.list = list;
        this.maxLength = maxLength;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    while (list.size() == maxLength) {
                        System.out.println("生产者" + Thread.currentThread().getName() + "队列已满，等待消费者消费，进入wait");
                        list.wait();
                        System.out.println("生成者" + Thread.currentThread().getName() + "退出wait");
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("生产者" + Thread.currentThread().getName() + "生成数据:" + i);
                    list.add(i);
                    list.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class Consumer implements Runnable {

    private List<Integer> list;

    public Consumer(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    while (list.isEmpty()) {
                        System.out.println("消费者" + Thread.currentThread().getName() + "队列已空，等待生产者生产，进入wait");
                        list.wait();
                        System.out.println("消费者" + Thread.currentThread().getName() + "退出wait");
                    }
                    int i = list.remove(0);
                    System.out.println("消费者" + Thread.currentThread().getName() + "消费数据:" + i);
                    list.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}