package tuacy.microservice.framweork.bootqueue.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tuacy.microservice.framweork.bootqueue.rabbitmq.model.SysUser;

/**
 * @program: microservice-framework
 * @description: 监听类       验证工作模式  -->  一个交换机一个队列， 多个消费者监听  不绑定key的情况
 * @author: hushenggui
 * @create: 2020-07-17 14:59
 **/
@Component
@Slf4j
public class RabbitListenerDirect {
    //
    public static final String exchangeName = "hsg-service-direct";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "work-direct", durable = "true"),
            key = "work-direct",
            exchange = @Exchange(value = exchangeName, ignoreDeclarationExceptions = "true"))
    )
    public void direct1(SysUser sysUser) {
        System.out.println();
        System.out.println("RabbitListener direct1 -- >  ");
        System.out.println(sysUser);
    }
}
