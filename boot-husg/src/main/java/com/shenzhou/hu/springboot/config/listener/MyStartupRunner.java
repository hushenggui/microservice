package com.shenzhou.hu.springboot.config.listener;

/**
 * 实际应用中，我们会有在项目服务启动的时候就去加载一些数据或做一些事情这样的需求。
 为了解决这样的问题，Spring Boot 为我们提供了一个方法，通过实现接口 CommandLineRunner 来实现。

 很简单，只需要一个类就可以，无需其他配置。

 @Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序。

 从大到小  执行顺序  监听器初始化 过滤器初始化 CommandLineRunner
 */


import com.shenzhou.hu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 服务启动执行
 *
 * @author   Angel(QQ:)
 */
@Component
@Order(value=1)
public class MyStartupRunner  implements CommandLineRunner {
    //Logger logger = Logger.getLogger("ceshi");

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
       // logger.info(">>>>>>>>>>>>>>>CommandLineRunner服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        //logger.warning(">>>>>>>>>>>>>>>CommandLineRunner服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");

      //  User user = userService.findOne(1);
        System.out.println(Arrays.asList(args));
        System.out.println(">>>>>>>>>>>>>>>CommandLineRunner服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");

    }
}
