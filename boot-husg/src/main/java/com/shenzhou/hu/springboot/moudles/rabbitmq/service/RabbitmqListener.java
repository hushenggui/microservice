package com.shenzhou.hu.springboot.moudles.rabbitmq.service;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 处理队列消息
 */
@Component
@Slf4j
public class RabbitmqListener {
        @RabbitListener(queues="foo",containerFactory = "rabbitListenerContainerFactory")
        public void dealQueen1(@Payload JSONObject object){
                log.info(" 1111>>> "+new Date() + ": " + object);
        }
}
