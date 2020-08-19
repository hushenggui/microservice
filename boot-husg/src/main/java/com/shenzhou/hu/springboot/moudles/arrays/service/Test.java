package com.shenzhou.hu.springboot.moudles.arrays.service;

import java.util.Arrays;

/**
 *length 只是数组的大小，并不能确定有多少元素。新生成一个数组时，其中所有的引用被自动初始化。根据类型初始化，对线类型全部为null.int 为0
 */
public class Test {


    public static void main(String[] args) {
        int[][] a = new int[3][2];
        int[][]   b = {
                {1,2},
                {3,4},
                {5,6}
        };
       // 上面的代码相当于定义了一个3*4的二维数组，即二维数组的长度为3，二维数组中的每个元素又是一个长度为4的数组
        int[ ][ ] array = new int[3][4];


        System.out.println(Arrays.deepToString(a));


        /**
         * 遍历二维数组
         */

        //二维数组的求累加和并遍历
        int[][] arr2 = { {1,2},{3,4,5},{6,7,8,9,10} };
        int sum2 = 0;
        for (int i=0; i<arr2.length; i++) {
            for (int j=0; j<arr2[i].length; j++) {
                //System.out.println(arr2[i][j])
                sum2 += arr2[i][j];
                System.out.println("arr2 "+arr2[i][j]);
            }
        }
        System.out.println("sum2= "+ sum2);


        /**
         * 练习题
         */
        int[][] arr = new int[3][]; // 定义一个长度为3的二维数组
        arr[0] = new int[] { 11, 12 }; // 为数组的元素赋值
        arr[1] = new int[] { 21, 22, 23 };
        arr[2] = new int[] { 31, 32, 33, 34 };

        int sum = 0; // 定义变量记录总销售额
        for (int i = 0; i < arr.length; i++) { // 遍历数组元素
            int groupSum = 0; // 定义变量记录小组销售总额
            for (int j = 0; j < arr[i].length; j++) { // 遍历小组内每个人的销售额
                groupSum = groupSum + arr[i][j];
            }
            sum = sum + groupSum; // 累加小组销售额
            System.out.println("第" + (i + 1) + "小组销售额为：" + groupSum + " 万元");
        }
        System.out.println("总销售额为: " + sum + " 万元");


        /**
         * 三维数组   第一个[]表示有多少个二维数组 这样理解
         */

        int[][][] three = {
                {
                        {1,2},
                        {4,5}
                },
                {
                        {12}
                }
        };
        System.out.println(Arrays.deepToString(three));


    }
}
