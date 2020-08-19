package com.shenzhou.hu.springboot.moudles.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import com.shenzhou.hu.springboot.moudles.rabbitmq.entity.MqQueue;
import com.shenzhou.hu.springboot.moudles.rabbitmq.utils.RabbitMQExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.logging.Logger;
@Slf4j
public class AmQueueDeclare extends AbstractDeclare{
    //Logger logger = Logger.getLogger("ceshi");

    /**
     * 声明队列
     * 向rabbitMQ服务器声明一个队列
     * @param mqQueue
     * @return
     */
    public Queue declareQueue(MqQueue mqQueue) {
        super.validate(mqQueue);
        Queue queue = new Queue(mqQueue.getName());
        BeanUtils.copyProperties(mqQueue, queue);
        super.rabbitAdmin.declareQueue(queue);
        return queue;
    }


    /**
     * 清空队列中的消息
     * @param queueName
     * @return 清楚队列中的消息的个数
     */
    public void purgeQueue(String queueName) {
        if (StringUtils.isEmpty(queueName)) {
            RabbitMQExceptionUtils.throwRabbitMQException();
        }
        super.rabbitAdmin.purgeQueue(queueName,true);
    }

    /**
     * 判断指定的队列是否存在
     * 1. 如果存在则返回该队列
     * 2. 如果不存在则返回null
     *
     * @param queueName
     * @return true 存在， false 不存在
     */
    public boolean isQueueExist(String queueName) {
        if (StringUtils.isEmpty(queueName)) {
            RabbitMQExceptionUtils.throwRabbitMQException();
        }

        RabbitTemplate rabbitTemplate = rabbitAdmin.getRabbitTemplate();
        if(rabbitTemplate == null){
            log.info("rabbitTemplate is null");
        }
        String isExist = super.rabbitAdmin.getRabbitTemplate().execute((channel -> {
            try {
                AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive(queueName);
                return declareOk.getQueue();
            } catch (Exception e) {

                return null;
            }
        }));
        return StringUtils.isEmpty(isExist) ? Boolean.FALSE : Boolean.TRUE;
    }


    /**
     * 从rabbitMQ服务器中删除指定的队列
     *
     * @param queueName
     * @return
     */
    public boolean deleteQueue(String queueName) {
        if (StringUtils.isEmpty(queueName)) {
            RabbitMQExceptionUtils.throwRabbitMQException();
        }

        return super.rabbitAdmin.deleteQueue(queueName);
    }

    @Override
    public void DefinedValidate(Object object) {

    }
}
