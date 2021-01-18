package com.nnynn.proxy.jdk;

import com.nnynn.proxy.service.UserService;
import com.nnynn.proxy.service.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author bo.luo
 * @date 2021/1/18 15:21
 */
public class JdkClient {

    public static void main(String[] args) {

        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        UserServiceImpl userService = new UserServiceImpl();
        ClassLoader classLoader = userService.getClass().getClassLoader();
        Class[] interfaces = userService.getClass().getInterfaces();

        InvocationHandler logHandler = new LogHandler(userService);

        UserService proxy = (UserService) Proxy.newProxyInstance(classLoader,interfaces,logHandler);
        proxy.select();

        proxy.update();
    }
}
