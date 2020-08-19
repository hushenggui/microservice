package com.shenzhou.hu.springboot.moudles.kafka.apiClient.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @program: ideaSpaceMy
 * @description: 自定义分区生产者
 * @author: hushenggui
 * @create: 2020-03-12 16:00
 **/
public class CustomPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //控制分区  可以根据key hash

        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
