package com.shenzhou.hu.springboot.config.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 使用@WebListener注解，实现ServletContextListener接口
 *
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
@WebListener

public class MyServletContextListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("***********  监听器 ServletContex初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("***********  监听器 ServletContex销毁");
    }
}
