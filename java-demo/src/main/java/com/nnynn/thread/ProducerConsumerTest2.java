package com.nnynn.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bo.luo
 * @date 2021/1/11 19:49
 */
public class ProducerConsumerTest2 {

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition full = lock.newCondition();

    private static Condition empty = lock.newCondition();


}
