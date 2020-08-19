package com.shenzhou.hu.springboot.moudles.algorithm.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @program: ideaSpaceBoot
 * @description: 工具类
 * @author: hushenggui
 * @create: 2020-04-15 15:27
 **/
public class ProxyUtil {
    @SuppressWarnings("unchecked")
    public static <T> T proxyOne(ClassLoader loader, Class<?>[] clz, InvocationHandler handler){
        return (T) Proxy.newProxyInstance(loader, clz, handler);
    }
}
