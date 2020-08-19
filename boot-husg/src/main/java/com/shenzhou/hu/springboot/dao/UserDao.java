package com.shenzhou.hu.springboot.dao;

import com.shenzhou.hu.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
}
