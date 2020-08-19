package com.shenzhou.hu.springboot.moudles.algorithm.jdkProxy;



/**
 * @program: ideaSpaceBoot
 * @description: 测试jdk动态代理
 * @author: hushenggui
 * @create: 2020-04-15 15:29
 **/
public class TestMain {


    public static void main(String[] args) {
        UserManager um=new UserManagerImpl();
        LogHandler log =new LogHandler(um);
        um=ProxyUtil.proxyOne(um.getClass().getClassLoader(),
                um.getClass().getInterfaces(), log);

        TimeHandler time = new TimeHandler(um);
        um=ProxyUtil.proxyOne(um.getClass().getClassLoader(),
                um.getClass().getInterfaces(), time);

        um.addUser("1111", "张三");

    }
}
