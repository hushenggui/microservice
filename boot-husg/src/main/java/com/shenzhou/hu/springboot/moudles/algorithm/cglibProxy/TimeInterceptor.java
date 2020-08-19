package com.shenzhou.hu.springboot.moudles.algorithm.cglibProxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: ideaSpaceBoot
 * @description: CGLIB动态代理的两个核心接口(类)分别是MethodInterceptor和Enhancer
 * @author: hushenggui
 * @create: 2020-04-15 15:43
 **/
public class TimeInterceptor implements MethodInterceptor {
    private Object target;
    public TimeInterceptor(Object target) {
        this.target = target;
    }
    @Override
    public Object intercept(Object proxy, Method method,
                            Object[] args, MethodProxy invocation) throws Throwable {
        System.out.println("方法之前："+System.currentTimeMillis());
        Object ret = invocation.invoke(target, args);
        System.out.println("方法之后："+System.currentTimeMillis());
        return ret;
    }
}
