package tuacy.microservice.framweork.bootqueue.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tuacy.microservice.framweork.bootqueue.rabbitmq.listener.RabbitChannelListener;
import tuacy.microservice.framweork.bootqueue.rabbitmq.listener.RabbitConnectionListener;
import tuacy.microservice.framweork.bootqueue.rabbitmq.listener.RabbitRecoveryListener;

import java.util.Map;

/**
 * @program: microservice-framework
 * @description: rabbitMQ配置类
 * @author: hushenggui
 * @create: 2020-07-17 17:19
 **/
@Configuration
@Slf4j
public class RabbitMQConfig {
    @Autowired
    public ConnectionFactory connectionFactory;

    @Autowired
    private RabbitConnectionListener rabbitConnectionListener;

    @Autowired
    private RabbitChannelListener rabbitChannelListener;

    @Autowired
    private RabbitRecoveryListener rabbitRecoveryListener;


    @Bean
    RabbitTemplate  rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.addConnectionListener(rabbitConnectionListener);
        connectionFactory.addChannelListener(rabbitChannelListener);
        connectionFactory.setRecoveryListener(rabbitRecoveryListener);
        RabbitTemplate  rabbitTemplate = new RabbitTemplate (connectionFactory);
        rabbitTemplate.setMandatory(true);
        /**
         * 消息是否成功发送到Exchange，每一条发出的消息都会调用ConfirmCallback
         * CorrelationData 消息的附加信息，即自定义id
         * isack 代表消息是否被broker（MQ）接收 true 代表接收 false代表拒收。
         * cause 如果拒收cause则说明拒收的原因，帮助我们进行后续处理
         */
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean isAck, String cause) ->{
            log.info("correlationData-----------" + correlationData);
            log.info("ack-----------" + isAck);
            if (isAck) {
                log.info("交换机接收成功 --> " + cause);
            }else{
                log.info("交换机拒绝接收 --> " + cause);
            }
        });

        /**
         * 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
         * 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有在消息进入exchange但没有进入queue时才会调用
         */
        rabbitTemplate.setReturnCallback((Message message, int code, String text, String exchange, String routingKey) ->{
            log.info("Code:" + code + ",text:" + text);
            log.info("Exchange:" + exchange + ",routingKey:" + routingKey);
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, code, text, message);
        });

        return rabbitTemplate;
    }
    /**
     * 注册rabbitAdmin 方便管理
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
