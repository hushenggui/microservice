package com.shenzhou.hu.springboot.moudles.annotation.service;

import com.shenzhou.hu.springboot.moudles.annotation.TestAnnotation;
import com.shenzhou.hu.springboot.moudles.annotation.entity.Car;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@TestAnnotation(id = 123,name = "testClass")
public class Test {


    //通过反射获取注解信息
    public static void main(String[] args) {
        Class test = Car.class;
        //判断是否有注解
        boolean isAnno = Car.class.isAnnotationPresent(TestAnnotation.class);
        boolean isAnnoTest = Test.class.isAnnotationPresent(TestAnnotation.class);


        System.out.println(isAnnoTest);
        if(isAnno){
            //通过 getAnnotation() 方法来获取 Annotation 对象
            TestAnnotation annotation = (TestAnnotation) test.getAnnotation(TestAnnotation.class);

            System.out.println("id:"+annotation.id());
            System.out.println("msg:"+annotation.name());
        }


        /**
         * 方法上的注解
         */

        //方法上的注解
        try {
            Method method = Car.class.getDeclaredMethod("getName");
            if(method != null){
                Annotation[] methodAnnotions = method.getAnnotations();
                for(Annotation methodAnnotion : methodAnnotions){
                    System.out.println("method testMethod annotation: "+methodAnnotion.annotationType().getSimpleName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }







}
