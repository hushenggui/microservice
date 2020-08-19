package com.shenzhou.hu.springboot.moudles.algorithm.jdkProxy;

/**
 * @program: ideaSpaceBoot
 * @description: UserManager实现类
 * @author: hushenggui
 * @create: 2020-04-15 15:26
 **/
public class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String userId, String userName) {
        System.out.println("addUser(id:"+userId+",name:"+userName+")");
    }
}
