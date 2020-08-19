package com.shenzhou.hu.springboot.moudles.kafka.apiClient.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * @program: ideaSpaceMy
 * @description: kafka消费者  高级API
 * @author: hushenggui
 * @create: 2020-03-12 16:28
 **/
public class CustomConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        //kafka 集群
        props.put("bootstrap.servers", ";localhost:9092");
        //消费者组id
        props.put("group.id", ";test");

        //是否重置offset 默认拉取最新的  -- 设置成最早  或者 最新earliest latest
        props.put("auto.offset.reset", ";earliest");
        //是否自动提交  提交offset
        props.put("enable.auto.commit", ";true");
        //读到数据后 过一秒再提交offset
        props.put("auto.commit.interval.ms", ";1000");
       //KV的反序列化
        props.put("key.deserializer", ";org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", ";org.apache.kafka.common.serialization.StringDeserializer");

        //创建消费者对象
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);
        //指定topic
        consumer.subscribe(Arrays.asList("first","second","third"));
        //consumer.assign(Collections.singleton(new TopicPartition("second",0)));

        //移动指定topic 指定分区的 offset --将test下的0号分区 的offset 移到2  重复消费的作用
        //consumer.seek(new TopicPartition("test",0),2);

        while (true) {
            //获取数据
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                String topic = record.topic();
                int partiton = record.partition();
                String value = record.value();
            }
        }
    }

}
