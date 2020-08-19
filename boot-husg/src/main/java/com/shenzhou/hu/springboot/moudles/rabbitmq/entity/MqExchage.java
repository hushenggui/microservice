package com.shenzhou.hu.springboot.moudles.rabbitmq.entity;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class MqExchage {
    /**
     * 交换机的名称
     */
    @NotNull(message = "交换机名称不能为空")
    private String name;

    /**
     * 交换机的类型
     */
    @NotNull(message = "交换机类型不能为空")
    private ExchangeTypeEnum type;
    /**
     * 是否持久化
     * 持久化可以将交换机存盘，在服务器重启的时候不会丢失相关的信息
     */
    private boolean durable;
    /**
     * 是否自动删除
     * 自动删除的前提是至少有一个队列或者交换机与这个交互机绑定，之后所有与这个交换机绑定的队列或者交换机都与此解绑
     */
    private boolean autoDelete;

    public MqExchage name(String name) {
        this.name = name;
        return this;
    }

    public MqExchage type(ExchangeTypeEnum type) {
        this.type = type;
        return this;
    }

    public MqExchage durable(boolean durable) {
        this.durable = durable;
        return this;
    }

    public MqExchage autoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
        return this;
    }

    /**
     * 自定义属性参数
     * 比如：alternate-exchange
     */
    private Map<String, Object> arguments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExchangeTypeEnum getType() {
        return type;
    }

    public void setType(ExchangeTypeEnum type) {
        this.type = type;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Object> arguments) {
        this.arguments = arguments;
    }
}
