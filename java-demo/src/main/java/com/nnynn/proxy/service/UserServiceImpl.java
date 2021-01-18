package com.nnynn.proxy.service;

/**
 * @author bo.luo
 * @date 2021/1/18 15:18
 */
public class UserServiceImpl implements UserService {
    @Override
    public void select() {
        System.out.println("查询用户信息 select");
    }

    @Override
    public void update() {
        System.out.println("更新用户信息 update");
    }
}
