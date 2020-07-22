package tuacy.microservice.framweork.bootqueue.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tuacy.microservice.framweork.bootqueue.rabbitmq.model.SysUser;

/**
 * @program: microservice-framework
 * @description: rabbitmq生产者
 * @author: hushenggui
 * @create: 2020-07-17 15:03
 **/
@Component
public class RabbitMQProducer {
    @Autowired
    RabbitTemplate rabbitTemplate;


    //工作模式
    public void sendMsg(){
        SysUser sysUser = new SysUser();
        sysUser.setName("zhangsan");
        sysUser.setRoutingKey("work1");
        rabbitTemplate.convertAndSend("", "work1", sysUser);
        sysUser.setRoutingKey("policyRevert_delay");
        rabbitTemplate.convertAndSend(RabbitListenerHandler.exchangeName, "policyRevert_delay", sysUser);
    }


    //fanout模式
    public void sendMsgFanout(){
        SysUser sysUser = new SysUser();
        sysUser.setName("zhangsan");
        sysUser.setRoutingKey("");
        rabbitTemplate.convertAndSend(RabbitListenerFanout.exchangeName, "", sysUser);
    }

    //topic模式
    public void sendMsgTopic(){
        SysUser sysUser = new SysUser();
        sysUser.setName("zhangsan");
        sysUser.setRoutingKey("work-topic.delay");
        rabbitTemplate.convertAndSend(RabbitListenerTopic.exchangeName, "work-topic.delay", sysUser);
        sysUser.setRoutingKey("work-topic.delay1");
        rabbitTemplate.convertAndSend(RabbitListenerTopic.exchangeName, "work-topic.delay1", sysUser);
        sysUser.setRoutingKey("work-topic.delay2");
        rabbitTemplate.convertAndSend(RabbitListenerTopic.exchangeName, "work-topic.delay2", sysUser);
    }


}
