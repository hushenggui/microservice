package tuacy.microservice.framweork.bootqueue.rabbitmq.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tuacy.microservice.framweork.bootqueue.rabbitmq.model.SysUser;

/**
 * @program: microservice-framework
 * @description: 手动确认监听类
 * @author: hushenggui
 * @create: 2020-08-26 09:47
 *
 *
 * （1）确认模式
 *
 * AcknowledgeMode.NONE：不确认
 * AcknowledgeMode.AUTO：自动确认
 * AcknowledgeMode.MANUAL：手动确认
 * spring-boot中配置方法：
 *
 * spring.rabbitmq.listener.simple.acknowledge-mode = manual
 *
 **/
@Component
@Slf4j
public class RabbitListenerManual {
    public static final String exchangeName = "hsg-service-manual";

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "work-manual", durable = "true"),
            key = "manual",
            exchange = @Exchange(value = exchangeName, ignoreDeclarationExceptions = "true"))
    )
    public void work1(@Payload SysUser sysUser, Channel channel, Message message) {
        System.out.println();
        System.out.println("RabbitListener manual -- >  ");
        System.out.println(sysUser);

        log.info("channel -- {}",channel);
        log.info("message -- {}",message);
        //测试非自动确认消息
        //反馈消息的状态
        long deliveryTag = (Long)message.getHeaders().get("amqp_deliveryTag");
        log.info("deliveryTag --- {}",deliveryTag);
        try {
            //deliveryTag 传递标签,long 类型.它的范围隶属于每个信道.因此必须在收到消息的相同信道上确认.不同的信道将导致“未知的传递标签”协议异常并关闭通道.
            //multiple 确认一条消息还是多条.false 表示只确认 e.DelivertTag 这条消息,true表示确认 小于等于 e.DelivertTag 的所有消息
            channel.basicAck(deliveryTag,false);
            //同一时刻只能发一条消息
            channel.basicQos(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
