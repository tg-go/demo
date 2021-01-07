package com.nnynn.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author bo.luo
 * @date 2021/1/7 10:05
 */
public class SPIDemo {

    public static void main(String[] args) {
        ServiceLoader<DemoService> serviceLoader = ServiceLoader.load(DemoService.class);
        System.out.println("加载完毕");
        Iterator<DemoService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            DemoService service = iterator.next();
            service.sayHello("luobo");
            System.out.println("class:"+service.getClass().getName());
        }

    }
}
