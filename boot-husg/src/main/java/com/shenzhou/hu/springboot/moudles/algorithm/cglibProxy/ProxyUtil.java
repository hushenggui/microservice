package com.shenzhou.hu.springboot.moudles.algorithm.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @program: ideaSpaceBoot
 * @description: 工具类
 * @author: hushenggui
 * @create: 2020-04-15 15:44
 **/
public class ProxyUtil {
    @SuppressWarnings("unchecked")
    public static <T> T proxyOne(Class<?> clz, MethodInterceptor interceptor){
        return (T) Enhancer.create(clz, interceptor);
    }
}
