package com.shenzhou.hu.springboot.moudles.mybatis.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 * @program: ideaSpaceBoot
 * @description: mybatis 拦截器
 * @author: hushenggui
 * @create: 2020-03-30 22:44
 **/
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // TODO: 自定义拦截逻辑
        return null;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("mybatis自定义拦截器 ===================================");
        return Plugin.wrap(target, this); // 返回代理类
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
