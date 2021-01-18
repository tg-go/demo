package com.nnynn.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author bo.luo
 * @date 2021/1/18 16:50
 */
public class StudentBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Student) {
            System.out.println("执行postProcessBeforeInitialization");
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Student) {
            System.out.println("执行postProcessAfterInitialization");
        }
        return bean;
    }
}
