package com.shenzhou.hu.springboot.moudles.mybatis.plus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: ideaSpaceBoot
 * @description: mp 配置类
 * @author: hushenggui
 * @create: 2020-03-29 23:13
 **/
//Spring boot方式

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
     * SQL输出拦截器
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //sql格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
    /**
     *分页查询拦截器
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        //设置方言类型
        page.setDialectType("mysql");
        return page;
    }
}
