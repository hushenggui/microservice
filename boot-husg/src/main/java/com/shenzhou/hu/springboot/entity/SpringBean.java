package com.shenzhou.hu.springboot.entity;

import org.springframework.stereotype.Component;

@Component("SpringBean")
public class SpringBean {
    private String name = "srping";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
