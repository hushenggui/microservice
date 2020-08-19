package com.shenzhou.hu.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

   @Id
   @GeneratedValue
    private String id;

    private String name;
}
