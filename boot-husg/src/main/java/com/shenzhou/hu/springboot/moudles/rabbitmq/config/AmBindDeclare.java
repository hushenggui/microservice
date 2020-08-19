package com.shenzhou.hu.springboot.moudles.rabbitmq.config;

import com.shenzhou.hu.springboot.moudles.rabbitmq.utils.RabbitMQExceptionUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: Bing绑定类
 * @author: 胡胜归
 * @create: 2019-10-23 19:46
 **/
public class AmBindDeclare extends AbstractDeclare {
    @Override
    public void DefinedValidate(Object object) {

    }

    /**
     * 队列与交换机进行绑定
     *
     * @param queueName    队列名称
     * @param exchangeName 交换机名称
     * @param routingKey   路由键
     * @return
     */
    public boolean queueBind(String queueName, String exchangeName, String routingKey) {
        return this.bing(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
    }

    /**
     * 交换机和交换机进行绑定
     *
     * @param destExchangeName 目标交换机名称
     * @param exchangeName     交换机名称
     * @param routingKey       路由键
     * @return
     */
    public boolean exchangeBind(String destExchangeName, String exchangeName, String routingKey) {
        return this.bing(destExchangeName, Binding.DestinationType.EXCHANGE, exchangeName, routingKey, null);
    }

    /**
     * bind绑定
     *
     * @param destName     目标名称（可以是队列 也可以是交换机）
     * @param type         绑定的类型 交换机 / 队列
     * @param exchangeName 交换机的名称
     * @param routingKey   路由键
     * @param map          结构参数
     * @return
     */
    private boolean bing(String destName, Binding.DestinationType type, String exchangeName, String routingKey, Map<String, Object> map) {
        /*this.logger.info("bind parameter is destName: " + destName + ", type: " + type.name()
                + ", exchangeName: " + exchangeName + ", routingKey: " + routingKey + ", map: " + map.toString());*/

        Binding binding = new Binding(destName, Binding.DestinationType.QUEUE, exchangeName, routingKey, map);
        try {
            this.rabbitAdmin.declareBinding(binding);
        } catch (Exception e) {
            RabbitMQExceptionUtils.throwRabbitMQException();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
