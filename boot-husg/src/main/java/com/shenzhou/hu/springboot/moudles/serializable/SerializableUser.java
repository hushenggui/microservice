package com.shenzhou.hu.springboot.moudles.serializable;

import java.io.*;

/**
 * @program: ideaSpaceBoot
 * @description: 序列化User类
 * @author: hushenggui
 * @create: 2020-04-01 12:20
 **/
public class SerializableUser {


    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo("张三", "123456");
        System.out.println(userInfo);
        ObjectOutputStream o = null;
        ObjectInputStream in = null;
        try {
            // 序列化，被设置为transient的属性没有被序列化
            o = new ObjectOutputStream(new FileOutputStream(
                    "D:\\UserInfo.out"));
            o.writeObject(userInfo);
            o.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            if(o != null){
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            // 重新读取内容
            in = new ObjectInputStream(new FileInputStream(
                    "D:\\UserInfo.out"));
            UserInfo readUserInfo = (UserInfo) in.readObject();
            //读取后psw的内容为null
            System.out.println(readUserInfo.toString());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
