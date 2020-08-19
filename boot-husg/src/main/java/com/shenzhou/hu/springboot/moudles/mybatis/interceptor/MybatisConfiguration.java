package com.shenzhou.hu.springboot.moudles.mybatis.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: ideaSpaceBoot
 * @description: mybatis 注册拦截器
 * @author: hushenggui
 * @create: 2020-03-30 22:48
 **/
@Configuration
public class MybatisConfiguration {

    /**
     * 注册拦截器
     */
    @Bean
    public MybatisInterceptor mybatisInterceptor() {
        System.out.println("mybatis注册拦截器 ===================================");
        MybatisInterceptor interceptor = new MybatisInterceptor();
        Properties properties = new Properties();
        // 可以调用properties.setProperty方法来给拦截器设置一些自定义参数
        interceptor.setProperties(properties);
        return interceptor;
    }
}
