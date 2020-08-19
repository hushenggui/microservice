package com.shenzhou.hu.springboot.moudles.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @description: 函数式接口
 * @author: 胡胜归
 * @create: 2019-10-21 17:53
 **/
public class FunctionInterfaceDemo {
    @FunctionalInterface
    interface Predicate<T> {
        boolean test(T t);

        //默认方法 可以多个
        default void test1() {

        }

        default void test2() {

        }
    }

    @FunctionalInterface
    interface red<T> {
        boolean test(T t);

    }
    /**
     * 执行Predicate判断
     *
     * @param age       年龄
     * @param predicate Predicate函数式接口
     * @return          返回布尔类型结果
     */
    public static boolean doPredicate(int age, Predicate<Integer> predicate) {
        return predicate.test(age);
    }

    public static void main(String[] args) {
        boolean isAdult = doPredicate(20, x -> x >= 18);
        //System.out.println(isAdult);
        foreachList();

        Runnable r = () -> {System.out.println("do something.");};
        Runnable b = () -> System.out.println("do something.");

        Predicate<Integer> p = (x) -> {
              System.out.println();
             return 1 > 2;
        };
        Predicate<Integer> p1  = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return false;
            }
        };


       boolean true1 =  p.test(1);

        System.out.println(" p " + p);
        System.out.println(" true1 " + true1);

        FunctionInterfaceDemo.C c = new FunctionInterfaceDemo.C();
        c.hello();

    }


    /**
     * @Description: 供给型接口示例
     * @Param:
     * @return:
     * @Author: 胡胜归
     * @Date: 2019/10/21
     * @throws
     */

    public static void foreachList(){
        List<Integer> list = supply(10,() -> (int)(Math.random()*100));
        //list.forEach(System.out::println);
        list.forEach(a -> {System.out.println(a);});
    }

    public static List<Integer> supply(Integer num, Supplier<Integer> supplier){
        List<Integer> resultList = new ArrayList<Integer>()   ;
        for(int x=0;x<num;x++) {
            resultList.add(supplier.get());
        }
        return resultList ;
    }


    interface A{
        default void hello() {
            System.out.println("from A");
        }
    }

    interface B extends A{
        @Override
        default void hello() {
            System.out.println("from B");
        }
    }

    static class C  implements B, A {

    }


    public static String returnStr(String a, String b){
        return a;
    }

}
