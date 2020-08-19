package com.shenzhou.hu.springboot.service;

import com.shenzhou.hu.springboot.entity.User;
import com.shenzhou.hu.springboot.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperService {
    @Autowired
    private DemoMapper demoMapper;
    public User findUserById(String id){
        return demoMapper.findUserById(id);
    }
}
