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
        sysUser.setName("lisi");
        rabbitTemplate.convertAndSend("", "work1", sysUser);
        rabbitTemplate.convertAndSend(RabbitListenerHandler.exchangeName, "work_delay", sysUser);
    }


    //fanout模式
    public void sendMsgFanout(){
        SysUser sysUser = new SysUser();
        sysUser.setName("zhangsan");
        sysUser.setName("lisi");
        rabbitTemplate.convertAndSend(RabbitListenerFanout.exchangeName, "", sysUser);
    }

}
