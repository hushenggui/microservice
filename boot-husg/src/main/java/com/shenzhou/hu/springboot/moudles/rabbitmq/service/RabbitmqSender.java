package com.shenzhou.hu.springboot.moudles.rabbitmq.service;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 根据类型处理队列消息
 */
@Component
@Configuration
//@RabbitListener(queues="foo")//启用Rabbit队列监听foo key.
@EnableScheduling
@Slf4j
//@PropertySource(value = {"classpath:/param.properties","file:${user.dir}/config/param.properties"},ignoreResourceNotFound=true)
public class RabbitmqSender {
    //rabbit操作类;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "${queenRabbitmqTaskJob}") // 每20秒执行一次
    public void send(){
        String json = "{\"hu\":\"123\"}";
        log.info("lombak : 开始发送消息 " + json);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(json.getBytes(),messageProperties);
        rabbitTemplate.send("","foo",message);
    }

    //接收到消息处理.
   /* @RabbitHandler
    public void onMessage(@Payload String foo){
        System.out.println(" 22222>>> foo"+new Date() + ": " + foo);
        log.info("lombak : 开始发送消息");
    }*/
}
