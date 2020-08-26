package tuacy.microservice.framweork.bootqueue.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tuacy.microservice.framweork.bootqueue.rabbitmq.service.RabbitMQProducer;

/**
 * @program: microservice-framework
 * @description: rabbitmq控制层
 * @author: hushenggui
 * @create: 2020-07-17 15:09
 **/
@RestController
public class RabbitController {
    @Autowired
    RabbitMQProducer rabbitMQProducer;

    //工作模式
    @RequestMapping("sendMsgWork")
    public String rabbitmqTest(){
        rabbitMQProducer.sendMsgWork();
        return "sendMsgWork";
    }

    //发布订阅模式
    @RequestMapping("sendMsgFanout")
    public String rabbitmqFanout(){
        rabbitMQProducer.sendMsgFanout();
        return "sendMsgFanout";
    }

    //主题模式
    @RequestMapping("sendMsgTopic")
    public String rabbitmqTopic(){
        rabbitMQProducer.sendMsgTopic();
        return "sendMsgTopic";
    }

    //手动确认
    @RequestMapping("sendMsgManual")
    public String rabbitmqManual(){
        rabbitMQProducer.sendMsgManual();
        return "sendMsgManual";
    }

    //私信队列
    @RequestMapping("sendMsgDelay")
    public String sendMsgDelay(){
        rabbitMQProducer.sendMsgDelay();
        return "sendMsgDelay";
    }

    //负载测试
    @RequestMapping("sendMsgFuzai")
    public String sendMsgFuzai(){
        rabbitMQProducer.sendMsgFuzai();
        return "sendMsgFuzai";
    }

}
