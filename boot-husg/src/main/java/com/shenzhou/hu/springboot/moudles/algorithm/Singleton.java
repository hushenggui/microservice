package com.shenzhou.hu.springboot.moudles.algorithm;

/**
 * @program: ideaSpaceMy
 * @description: 单例模式
 * @author: hushenggui
 * @create: 2020-03-21 20:37
 **/
public class Singleton {
    private volatile static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if(instance==null) {
            synchronized (Singleton.class) {
                if(instance==null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
