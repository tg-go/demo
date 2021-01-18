package com.nnynn.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author bo.luo
 * @date 2021/1/18 15:16
 */
public class LogHandler implements InvocationHandler {
    // 被代理对象
    private Object target;

    public LogHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    public void before(){
        System.out.println(String.format("log start time [%s] ", new Date()));
    }

    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}
