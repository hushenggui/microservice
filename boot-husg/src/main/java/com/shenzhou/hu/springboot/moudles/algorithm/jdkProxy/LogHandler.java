package com.shenzhou.hu.springboot.moudles.algorithm.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: ideaSpaceBoot
 * @description: 12
 * @author: hushenggui
 * @create: 2020-04-15 15:35
 **/
public class LogHandler implements InvocationHandler {

    // 目标对象
    private Object targetObject;

    public LogHandler(Object targetObject){
        this.targetObject = targetObject;
    }
    @Override
    //关联的这个实现类的方法被调用时将被执行
    /*InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，
        args表示方法的参数*/
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object ret=null;
        try{
            System.out.println("方法之前："+System.currentTimeMillis());
            //调用目标方法
            ret=method.invoke(targetObject, args);
            System.out.println("方法之后："+System.currentTimeMillis());
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error");
            throw e;
        }
        return ret;
    }

}