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
 * @description: 发布订阅Fanout模式  一个队列可以绑定到多个交换机 work1 既在direct 又在 fanout
 * @author: hushenggui
 * @create: 2020-07-20 10:49
 **/
@Component
@Slf4j
public class RabbitListenerFanout {
    //
    public static final String exchangeName = "hsg-service-fanout";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout1", durable = "true"),
            exchange = @Exchange(value = exchangeName,type = ExchangeTypes.FANOUT,ignoreDeclarationExceptions = "true"))
    )
    public void fanout1(SysUser sysUser) {
        System.out.println();
        System.out.println("RabbitListener fanout1 -- >  ");
        System.out.println(sysUser);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout2", durable = "true"),
            exchange = @Exchange(value = exchangeName,type = ExchangeTypes.FANOUT,ignoreDeclarationExceptions = "true"))
    )
    public void fanout2(SysUser sysUser) {
        System.out.println();
        System.out.println("RabbitListener fanout2 -- >  ");
        System.out.println(sysUser);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "fanout3", durable = "true"),
            exchange = @Exchange(value = exchangeName,type = ExchangeTypes.FANOUT,ignoreDeclarationExceptions = "true"))
    )
    public void fanout3(SysUser sysUser) {
        System.out.println();
        System.out.println("RabbitListener fanout3 -- >  ");
        System.out.println(sysUser);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "work1", durable = "true"),
            exchange = @Exchange(value = exchangeName,type = ExchangeTypes.FANOUT,ignoreDeclarationExceptions = "true"))
    )
    public void fanout4(SysUser sysUser) {
        System.out.println();
        System.out.println("RabbitListener work1 -- >  ");
        System.out.println(sysUser);
    }
}
