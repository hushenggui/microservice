package com.shenzhou.hu.springboot.moudles.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j()
public class RabbitmqConfig {
    @Value("${listenerQueens}")
    public String queens;

    /** 
    * @Description: 默认的 SimpleMessageConverter 在发送消息时会将对象序列化成字节数组，若要反序列化对象，需要自定义 MessageConverter
     * 1 使用 JSON 序列化与反序列化  2 内容对象序列化与反序列化
    * @Param: [connectionFactory] 
    * @return: org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory<?> 
    * @Author: 胡胜归
    * @Date: 2019/10/21 
    * @throws  
    */ 
   /* @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //对象序列化
        *//*factory.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
                return null;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(message.getBody()))){
                    return ois.readObject();
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }
        });*//*

        //JSON 序列化与反序列化
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
*/

    //就可以通过rabbitTemplate.convertAndSend(createCustomerQueueName, requestWrapper);来自动序列化成json字符串了。
    @Autowired
    public ConnectionFactory connectionFactory;

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }



    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory mqConnectionFactory) {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(mqConnectionFactory);
        //--加上这句
        listenerContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        return listenerContainerFactory;
    }

    @Bean
    public Queue fooQueue(){
       /* AmQueueDeclare queueDeclare = new AmQueueDeclare();
        if(!queueDeclare.isQueueExist("foo")){
            return new Queue("foo");
        }
        return null;*/

        return new Queue("foo");
    }

    @Bean
    public Queue foo1Queue(){
        return new Queue("foo1");
    }


    /**
     * 确认消息（全局处理消息）
     */

    //自动确认涉及到一个问题就是如果在处理消息的时候抛出异常，消息处理失败，但是因为自动确认而导致 Rabbit 将该消息删除了，造成消息丢失
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        String[] arr = queens.split(",");
        // 监听的队列
        container.setQueueNames(arr);
        // NONE 代表自动确认
        container.setAcknowledgeMode(AcknowledgeMode.NONE);
        //消息监听处理
        container.setMessageListener((MessageListener) (message) -> {
            log.info("====接收到消息=====");
            log.info("消息主体" + new String(message.getBody()));
            //相当于自己的一些消费逻辑抛错误
            //throw new NullPointerException("consumer fail");
        });
        return container;
    }

    //手动确认 消息  与自动确认二者选其一
   /* @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        log.info("监听的队列 " + queens);
        container.setQueueNames(queens);              // 监听的队列
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);        // 手动确认
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {      //消息处理
            log.info("====接收到消息=====");
            log.info("消息主体" + new String(message.getBody()));
            log.info("error :" + message.getMessageProperties().getHeaders().get("error"));
            if(message.getMessageProperties().getHeaders().get("error") == null){
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                log.info("消息已经确认");
            }else {
                //重新入队列然后重新消费
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                //拒接消息 消息会被丢弃，不会重回队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                log.error("消息拒绝");
            }

        });
        return container;
    }*/

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

