package com.shenzhou.hu.springboot.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

       /* List<String> list = new ArrayList<>(Arrays.asList("key","yek","sin","ins","pat","eyk"));


        List<ArrayList<String>> list1 = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        list1.add(stringArrayList);
        for (int i =0;i<list.size();i++){
            String str = list.get(i);
            int sum = SumStrAscii(str);
            for(int j =0;j<list1.size();j++){
                List list2 = list1.get(j);
                if(list2.size()>0){
                    String str1  = (String)list2.get(0);
                    if(sum == SumStrAscii(str1)){
                        list2.add(str1);
                    }
                }
            }
            ArrayList<String> stringArrayList1 = new ArrayList<>();
            stringArrayList1.add(str);
            list1.add(stringArrayList1);
        }*/

        System.out.println("123");

        String str = "拟上云(12)个";
        String s = str.substring(str.indexOf("("),str.length());
        System.out.println(str.indexOf("("));



        /*int sum = (x) ->{
            int a = 0;
            return a;

        };
*/

    }
    public static int SumStrAscii(String str){
        byte[] bytestr = str.getBytes();
        int sum = 0;
        for(int i=0;i<bytestr.length;i++){
            sum += bytestr[i];
        }
        return sum;
     }


}
