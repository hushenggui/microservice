package tuacy.microservice.framweork.bootqueue.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: microservice-framework
 * @description: rabbitMQ配置类
 * @author: hushenggui
 * @create: 2020-07-17 17:19
 **/
@Configuration
@Slf4j
public class RabbitMQConfig {

    @Bean
    RabbitTemplate  rabbitTemplate() {
        RabbitTemplate  rabbitTemplate = new RabbitTemplate ();
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        return rabbitTemplate;
    }

    @Autowired
    public ConnectionFactory connectionFactory;

    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory mqConnectionFactory) {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(mqConnectionFactory);
        //--加上这句
        listenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        return listenerContainerFactory;
    }


    /**
     * CorrelationData 消息的附加信息，即自定义id
     * isack 代表消息是否被broker（MQ）接收 true 代表接收 false代表拒收。
     * cause 如果拒收cause则说明拒收的原因，帮助我们进行后续处理
     */
    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean isAck, String cause) {
            log.info("correlationData-----------" + correlationData);
            log.info("ack-----------" + isAck);
            if (isAck == false) {
                log.info("拒绝接收" + cause);
            }

        }
    };

    RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int code, String text, String exchange, String routingKey) {
            log.info("Code:" + code + ",text:" + text);
            log.info("Exchange:" + exchange + ",routingKey:" + routingKey);
        }
    };

}
