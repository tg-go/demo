package com.nnynn.mybatis.model;

import org.apache.ibatis.type.Alias;

/**
 * @author bo.luo
 * @date 2020/12/11 16:57
 */
public class Demo {

    private long id;

    private String name;

    private int age;

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
