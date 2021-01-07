package com.nnynn.spi;

/**
 * @author bo.luo
 * @date 2021/1/7 10:02
 */
public class ChineseDemoServiceImpl implements DemoService {

    public ChineseDemoServiceImpl() {
        System.out.println("创建中文的");
    }

    @Override
    public void sayHello(String msg) {
        System.out.println("你好," + msg);
    }
}
