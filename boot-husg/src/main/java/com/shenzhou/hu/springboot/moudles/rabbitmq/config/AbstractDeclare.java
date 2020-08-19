package com.shenzhou.hu.springboot.moudles.rabbitmq.config;

import com.shenzhou.hu.springboot.moudles.rabbitmq.utils.RabbitMQExceptionUtils;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;

public abstract  class AbstractDeclare {

    @Autowired
    RabbitAdmin rabbitAdmin;

    /**
     * 自定义的校验
     *
     * @param object
     */
    public abstract void DefinedValidate(Object object);

    /**
     * 通用校验
     * 1. 校验字段是否是非空
     *
     * @param object
     */
    public void validate(Object object) {
        this.DefinedValidate(object);
    }
}
