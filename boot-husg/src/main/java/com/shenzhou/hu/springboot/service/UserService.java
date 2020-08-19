package com.shenzhou.hu.springboot.service;

import com.shenzhou.hu.springboot.dao.UserDao;
import com.shenzhou.hu.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    //查询所有
    @Transactional
    @Cacheable(value="users",key = "'userList'")
    public List<User>  findAll(){
       return userDao.findAll();
    }

    //查找单个
   @Transactional
  // @Cacheable(value="users", key="'user_'+#id", condition="#id!='0'")
   public User findOne(String id){
       return   userDao.findById(id).get();
    }

 //   @CacheEvict(value="users", key="'user_'+#id",condition="#id!='0'")
    public void deleteUser(String id){
        userDao.deleteById(id);
    };

      @Cacheable(value="hu", key="'hu456'")
       public String findtt(){
           return "hu456";
       }
}
