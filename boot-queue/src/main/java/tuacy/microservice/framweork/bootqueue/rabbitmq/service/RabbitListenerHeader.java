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
public class RabbitListenerHeader {
    //
    public static final String exchangeName = "hsg-service-header";

   /* @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "work-header", durable = "true"),
            key = "work-header",
            exchange = @Exchange(value = exchangeName, ignoreDeclarationExceptions = "true")
    )
    )
    public void header1(SysUser sysUser) throws Exception {
        System.out.println();
        System.out.println("RabbitListener header1 -- >  ");
        System.out.println(sysUser);
        throw  new Exception();
    }

    public void topicErrorHaddler(){
        System.out.println();
        System.out.println("topicErrorHaddler  -- >  error"   );
    }*/
}
