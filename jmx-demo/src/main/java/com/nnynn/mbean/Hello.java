package com.nnynn.mbean;

/**
 * @author bo.luo
 * @date 2020/12/15 10:36
 */
public class Hello implements HelloMBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello() {
        System.out.println("hello," + name);
    }

    public void printHello(String name) {
        System.out.println("hello," + name);
    }
}
