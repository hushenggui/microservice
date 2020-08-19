package com.shenzhou.hu.springboot.moudles.mybatis.plus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue
    private String id;
    private String uname;
    private String password;
    private String sex;
    public User(String uname, String sex) {
        this.uname = uname;
        this.sex = sex;
    }

    public User(String id, String uname,String password ) {
        this.password = password;
        this.uname = uname;
        this.id = id;
    }
}
