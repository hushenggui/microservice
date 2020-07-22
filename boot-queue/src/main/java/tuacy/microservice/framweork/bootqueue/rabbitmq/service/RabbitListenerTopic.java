package tuacy.microservice.framweork.bootqueue.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tuacy.microservice.framweork.bootqueue.rabbitmq.model.SysUser;

/**
 * @program: microservice-framework
 * @description: 监听类       验证主题模式
 * @author: hushenggui
 * @create: 2020-07-17 14:59
 **/
@Component
@Slf4j
public class RabbitListenerTopic {
    //
    public static final String exchangeName = "hsg-service-topic";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "work-topic", durable = "true"),
            key = "work-topic.*",
            exchange = @Exchange(value = exchangeName, type = ExchangeTypes.TOPIC,ignoreDeclarationExceptions = "true"))
    )
    public void topic1(SysUser sysUser) {
        System.out.println();
        System.out.println("RabbitListener topic1 -- >  ");
        System.out.println(sysUser);
    }

}
