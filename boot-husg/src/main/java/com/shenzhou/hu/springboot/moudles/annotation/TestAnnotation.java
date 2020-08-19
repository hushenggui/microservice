package com.shenzhou.hu.springboot.moudles.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 ElementType.CONSTRUCTOR 可以给构造方法进行注解
 ElementType.FIELD 可以给属性进行注解
 ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 ElementType.METHOD 可以给方法进行注解
 ElementType.PACKAGE 可以给一个包进行注解
 ElementType.PARAMETER 可以给一个方法内的参数进行注解
 ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//注解类要加上@Retention(RetentionPolicy.RUNTIME)表示该注解在运行时存在
public @interface TestAnnotation {
     String name();
     int id();
}
