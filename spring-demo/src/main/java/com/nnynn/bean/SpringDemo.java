package com.nnynn.bean;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * @author bo.luo
 * @date 2021/1/18 16:52
 */
public class SpringDemo {

    public static void main(String[] args) {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring.xml"));
        //XmlWebApplicationContext context = new XmlWebApplicationContext(new ClassPathResource("spring.xml"));
        Student student= beanFactory.getBean(Student.class);
        ApplicationContext applicationContext = new XmlWebApplicationContext();
        applicationContext.getBean("aa");
        System.out.println(student);
        beanFactory.destroyBean(student);
    }
}
