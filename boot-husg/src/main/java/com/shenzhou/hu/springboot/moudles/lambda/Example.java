package com.shenzhou.hu.springboot.moudles.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: springboot
 * @description: 双冒号测试
 * @author: hushenggui
 * @create: 2020-07-28 15:07
 **/
public class Example {

    private String name;
    Example(String name){
        this.name = name;
    }

    Example(){
    }


    public static void main(String[] args) {
        InterfaceExample com =  Example::new;
        Example bean = com.create("hello world");
        System.out.println(bean.name);

        List<String> list = Arrays.asList("aaaa", "bbbb", "cccc");

        //对象实例语法	instanceRef::methodName
        list.forEach(new Example()::testA);
    }





    public void testA(String a){
        System.out.println("testA");
    }


}


