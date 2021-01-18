package com.nnynn.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * @author bo.luo
 * @date 2021/1/18 16:46
 */
public class Student implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String name;

    public Student() {
        System.out.println("student的无惨构造函数");
    }

    public void setName(String name) {
        System.out.println("设置student的name属性");
        this.name = name;
    }

    public void initStudent(){
        System.out.println("执行init-method");
    }

    public void destroyStudent(){
        System.out.println("执行destroy-method");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("设置student的beanFactory=" + name);
    }

    public void setBeanName(String name) {
        System.out.println("设置student的beanName=" + name);
    }

    public void destroy() throws Exception {
        System.out.println("执行destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("执行afterPropertiesSet");
    }
}
