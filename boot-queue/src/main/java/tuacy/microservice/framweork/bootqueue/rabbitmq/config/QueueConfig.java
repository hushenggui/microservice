package tuacy.microservice.framweork.bootqueue.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tuacy.microservice.framweork.bootqueue.rabbitmq.service.RabbitListenerDelay;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: microservice-framework
 * @description: 队列定义类
 * @author: hushenggui
 * @create: 2020-07-24 14:49
 **/
@Configuration
public class QueueConfig {
    /*@Bean
    @Qualifier(value = RabbitListenerDelay.QUEUEDELAY_DELAY)
    public Queue queueDelay(){
        Map<String, Object> arguments = new HashMap<String,Object>();
        arguments.put("x-dead-letter-exchange", RabbitListenerDelay.exchangeName);
        arguments.put("x-dead-letter-routing-key","queueDelay");
        arguments.put("x-message-ttl",20000);
        arguments.put("x-queue-type","classic");
        Queue queue = new Queue(RabbitListenerDelay.QUEUEDELAY_DELAY,true,false,false,arguments);
       // rabbitAdminUtil.queueBind("queueDelay_delay",RabbitListenerDelay.exchangeName,"queueDelay_delay");
        return queue;
    }*/


    /**
     * 延迟队列（死信队列）
     */
    @Bean
    @Qualifier(RabbitListenerDelay.QUEUEDELAY_DELAY)
    public Queue projectTtlQueue() {
        return QueueBuilder
                .durable(RabbitListenerDelay.QUEUEDELAY_DELAY)
                .withArgument("x-dead-letter-exchange", RabbitListenerDelay.exchangeName)//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", "queueDelay")//到期后转发的路由键
                .withArgument("x-message-ttl",20000)//毫秒
                .withArgument("x-queue-type","classic")
                .build();
    }

    /**
     * 交换器
     *
     * @return
     */
    @Bean
    @Qualifier(value = RabbitListenerDelay.exchangeName)
    DirectExchange projectExchange() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(RabbitListenerDelay.exchangeName)
                .durable(true)
                .build();
    }

    /**
     * 声明绑定关系
     * 将队列绑定到交换机
     *
     * @return
     */
    @Bean
    Binding projectBinding(@Qualifier(RabbitListenerDelay.exchangeName) DirectExchange exchange,
                           @Qualifier(RabbitListenerDelay.QUEUEDELAY_DELAY) Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("queueDelay_delay");
    }

}
