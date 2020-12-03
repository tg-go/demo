package com.nnynn.thread;

import org.junit.Test;

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
}
