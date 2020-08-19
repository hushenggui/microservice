package com.shenzhou.hu.springboot.moudles;

/**
 * @description: 测试类
 * @author: 胡胜归
 * @create: 2019-09-06 14:48
 **/
public class Test  {
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

    public static void main(String[] args) {
       Test.C c = new Test.C();
       c.hello();
        Thread t = new Thread();
        /*ArrayList<String> list = new ArrayList<>();
        List<String> synList= Collections.synchronizedList(list);*/

        int a = 2;
        a--;
        System.out.println(a);

    }


}
