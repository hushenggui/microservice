package com.shenzhou.hu.springboot.moudles.java8.service;

import ch.qos.logback.core.pattern.Converter;
import com.shenzhou.hu.springboot.moudles.java8.entity.Person;

/**
 * @description: 测试java8新特性
 * @author: 胡胜归
 * @create: 2019-12-03 20:07
 **/
public class TestJava8 {
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }



    public static void main(String[] args) {

        //构造函数的应用
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");


        Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);
        Integer converted1 = converter1.convert("123");
        System.out.println(converted1);


        TestJava8 something = new TestJava8();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"
    }


}
