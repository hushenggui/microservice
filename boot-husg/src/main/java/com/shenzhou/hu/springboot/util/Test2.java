package com.shenzhou.hu.springboot.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {

    private static final byte[] I1 = new byte[]{80, 79, 83, 84};
    public static void main(String[] args) {
       /* int[] a = {10,12,3,4,7,13};
        bubblingSort(a);
        for(int i = 0 ;i<a.length-1;i++){
            System.out.println(a[i]);
        }*/
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Java");
        list.add("Scala");
        list.add("Python");
        list.add("Html");
        list.add("css");
        list.add("JavaScript");
        list.add("js");
        list.add("Hadoop");
        list.add("C");
        list.add("redis");
        list.add("Java");

        list.forEach(System.out::println);

        String  temp = new String(I1);
        System.out.println(temp);


     }




    //冒泡
    public static void  bubblingSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]>array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }
    }

    private static void swap(int[] array,int x,int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
