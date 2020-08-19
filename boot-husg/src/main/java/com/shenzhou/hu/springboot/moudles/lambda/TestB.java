package com.shenzhou.hu.springboot.moudles.lambda;

/**
 * @description: 测试A的实现类
 * @author: 胡胜归
 * @create: 2019-11-25 17:17
 **/
public class TestB implements TestA {



    public static void main(String[] args) {
        TestB b = new TestB();

        int a = 1;
        TestA r = (c) -> {System.out.println(c);};
        r.methodA1(1);
    }

    @Override
    public void methodA1(Integer A) {

    }
}
