package com.shenzhou.hu.springboot.util;

/**
 * @program: ideaSpaceBoot
 * @description: 字符串工具类
 * @author: hushenggui
 * @create: 2020-04-10 17:15
 **/
public class StringUtils {
    
    /**
    * @Description: 判断一个字符串是不是正整数
    * @Param: [str]
    * @return: 
    * @Author: hushenggui
    * @Date: 2020/4/10
    */
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
