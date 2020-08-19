package com.shenzhou.hu.springboot.moudles.rabbitmq.utils;

import com.shenzhou.hu.springboot.moudles.rabbitmq.entity.MqQueue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: rabbitmq工具类
 * @author: 胡胜归
 * @create: 2019-10-22 18:48
 **/

public class RabbitmqUtils {
    /** 
    * @Description: 判断队列是否存在
    * @Param:  
    * @return:  
    * @Author: 胡胜归
    * @Date: 2019/10/22 
    * @throws  
    */

    @Autowired
    RabbitAdmin rabbitAdmin;

    /**
    * @Description: 发送消息
    * @Param:
    * @return:
    * @Author: 胡胜归
    * @Date: 2019/10/23
    * @throws
    */

    public void sendMessage(MqQueue mqQueue){


    }


}
