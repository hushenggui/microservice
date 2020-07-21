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

    @RequestMapping("sendMsg")
    public String rabbitmqTest(){
        rabbitMQProducer.sendMsg();
        return "Success";
    }


    @RequestMapping("sendMsgFanout")
    public String rabbitmqFanout(){
        rabbitMQProducer.sendMsgFanout();
        return "Success";
    }

}
