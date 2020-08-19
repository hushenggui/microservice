package com.shenzhou.hu.springboot.moudles.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import com.shenzhou.hu.springboot.moudles.rabbitmq.entity.ExchangeTypeEnum;
import com.shenzhou.hu.springboot.moudles.rabbitmq.entity.MqExchage;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 路由器的注册于销毁
 */
public class AmExchangeDeclare extends AbstractDeclare{

    /**
     * 向rabbitMQ服务器注册指定的交换机以及交换机的类型
     *
     * @param mqExchage
     * @return
     */
    public Exchange declareExchange(MqExchage mqExchage) {
        Exchange exchange = null;
        //super.validate(mqExchage);
        exchange = this.initExchange(mqExchage);
        super.rabbitAdmin.declareExchange(exchange);
        //this.logger.info("declare exchange success");
        return exchange;
    }


    /**
     * 根据不同类型初始化不同类型的交换机
     *
     * @param mqExchage
     * @return
     */
    private Exchange initExchange(MqExchage mqExchage) {
        ExchangeTypeEnum exchangeTypeEnum = mqExchage.getType();
        switch (exchangeTypeEnum) {
            case DIRECT:
                return new DirectExchange(mqExchage.getName(), mqExchage.isDurable(), mqExchage.isAutoDelete(), mqExchage.getArguments());
            case TOPIC:
                return new TopicExchange(mqExchage.getName(), mqExchage.isDurable(), mqExchage.isAutoDelete(), mqExchage.getArguments());
            case FANOUT:
                return new FanoutExchange(mqExchage.getName(), mqExchage.isDurable(), mqExchage.isAutoDelete(), mqExchage.getArguments());
            case HEADERS:
                return new HeadersExchange(mqExchage.getName(), mqExchage.isDurable(), mqExchage.isAutoDelete(), mqExchage.getArguments());
            default:
                return null;
        }
    }


    @Override
    public void DefinedValidate(Object object) {

    }
}
