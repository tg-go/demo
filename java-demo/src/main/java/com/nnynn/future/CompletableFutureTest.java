package com.nnynn.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author bo.luo
 * @date 2021/1/25 16:28
 */
public class CompletableFutureTest {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->"Hello World");
        System.out.println(future.get());
    }

    @Test
    public void allOfTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            System.out.println("Hello Wordl start1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello World wake");
            return "Hello World 1 finish";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()->{
            System.out.println("Hello Word2 start1");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello World2 wake");
            return "Hello World 2 finish";
        });

        // 没有返回结果
        //CompletableFuture allFuture = CompletableFuture.allOf(future1,future2);
        // 返回最早的一个Future
        CompletableFuture anyFuture = CompletableFuture.anyOf(future1,future2);
        System.out.println(anyFuture.get());
    }
}
