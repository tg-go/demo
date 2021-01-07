package com.nnynn.spi;

/**
 * @author bo.luo
 * @date 2021/1/7 10:03
 */
public class EnglishDemoServiceImpl implements DemoService {

    public EnglishDemoServiceImpl() {
        System.out.println("创建英文的");
    }

    @Override
    public void sayHello(String msg) {
        System.out.println("Hello," + msg);
    }
}
