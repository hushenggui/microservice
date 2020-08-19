package com.shenzhou.hu.springboot.moudles.algorithm.cglibProxy;

/**
 * @program: ideaSpaceBoot
 * @description: 测试类
 * @author: hushenggui
 * @create: 2020-04-15 15:47
 **/
public class TestMain {
    public static void main(String[] args) {
        UserManage um = new UserManage();
        TimeInterceptor time = new TimeInterceptor(um);
        um = ProxyUtil.proxyOne(um.getClass(), time);
        um.addUser("111", "老王");
    }
}
