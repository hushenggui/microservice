package com.shenzhou.hu.springboot.controller;

import com.shenzhou.hu.springboot.entity.User;
import com.shenzhou.hu.springboot.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/b")
public class MyBatisController {

    @Autowired
    private MapperService mapperService;

    @RequestMapping("/Myfind")
    public String findUserById(){
       User user = mapperService.findUserById("1");
       System.out.println(user.getId());
        return "/hello";
    }
}
