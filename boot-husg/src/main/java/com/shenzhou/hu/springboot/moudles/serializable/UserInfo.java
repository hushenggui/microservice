package com.shenzhou.hu.springboot.moudles.serializable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ideaSpaceBoot
 * @description: 用户信息model
 * @author: hushenggui
 * @create: 2020-04-01 12:22
 **/
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 996890129747019948L;
    private String name;
    //transient 关键字表示不会被序列化
    private transient String psw;

    public UserInfo(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }

    HashMap map = new HashMap();
    public String toString() {
        return "name=" + name + ", psw=" + psw;
    }
}
