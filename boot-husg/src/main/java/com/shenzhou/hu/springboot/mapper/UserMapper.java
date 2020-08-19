package com.shenzhou.hu.springboot.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.hu.springboot.moudles.mybatis.plus.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
