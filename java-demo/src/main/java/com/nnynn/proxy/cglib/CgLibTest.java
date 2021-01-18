package com.nnynn.proxy.cglib;

import com.nnynn.proxy.service.UserDao;
import net.sf.cglib.proxy.Enhancer;

import java.io.IOException;

/**
 * @author bo.luo
 * @date 2021/1/18 15:33
 */
public class CgLibTest {

    public static void main(String[] args) throws IOException {

        LogInterceptor dao = new LogInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        enhancer.setCallback(dao);

        UserDao userDao = (UserDao) enhancer.create();
        userDao.select();
        userDao.update();

        System.in.read();

    }
}
