package com.shenzhou.hu.springboot.moudles.rabbitmq.utils;

public class RabbitMQExceptionUtils {


    public static void throwRabbitMQException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
