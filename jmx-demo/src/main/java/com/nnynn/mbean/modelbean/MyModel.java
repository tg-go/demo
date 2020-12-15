package com.nnynn.mbean.modelbean;

/**
 * @author bo.luo
 * @date 2020/12/15 16:39
 */
public class MyModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("Hello World," + name);
    }

    public void printHello(String name) {
        System.out.println("Hello World," + name);
    }
}
