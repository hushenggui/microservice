package com.shenzhou.hu.springboot.mapper;

import com.shenzhou.hu.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DemoMapper {
     @Select("")
     public User findUserById(String id);
}
