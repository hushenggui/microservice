package com.shenzhou.hu.springboot.moudles.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenzhou.hu.springboot.SpringbootApplication;
import com.shenzhou.hu.springboot.mapper.UserMapper;
import com.shenzhou.hu.springboot.moudles.mybatis.plus.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: ideaSpaceBoot
 * @description: mybatisPlus 测试类
 * @author: hushenggui
 * @create: 2020-03-29 22:56
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@Slf4j
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testInsert(){
        User user = new User();
        user.setUname("东方不败");
        user.setPassword("dfbb@163.com");
        user.setSex("1");
        user.setId("2");
        userMapper.insert(user);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(user.getId());
    }


    @Test
    public void testPlus(){
        //根据map查询list
        Map map = new HashMap();
        map.put("uname", "李白");
        List<User> u1 =  userMapper.selectByMap(map);

        //根据id修改用户 没有传值的属性不会更新
        User user = new User("2","libai","123");
        Integer index = userMapper.updateById(user);
        //根据id查询用户
        User u2 = userMapper.selectById("2");
        //
    }


    /**
     * @Description: 分页查询
     * @Param: [pageNumber, pageSize]
     * @return:
     * @Author: hushenggui
     * @Date: 2020/3/30
     */
    @Test
    public void getUserListByPage()
    {
        Page<User> page =new Page<>(1,1);
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("uname","李白");
        //wrapper.between("id",1,3);
        IPage page1 =  userMapper.selectPage(page,wrapper);
        log.info(page1.getRecords().toString());
    }


    /**
     * @Description: 通过id批量查询
     * @Param: []
     * @return:
     * @Author: hushenggui
     * @Date: 2020/3/30
     */
    @Test
    public void  getUsetListByIdList(){
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        List<User> users = userMapper.selectBatchIds(idList);
        log.info(users.toString());
    }

    /**
    * @Description: 删除
    * @Param: []
    * @return: 
    * @Author: hushenggui
    * @Date: 2020/3/30
    */
    @Test
    public void testDelete(){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("uname","123");
        columnMap.put("sex","1");
        int  result = userMapper.deleteByMap(columnMap);
        //根据id删除
        userMapper.deleteById("1");
        log.info(String.valueOf(result));
    }

}
