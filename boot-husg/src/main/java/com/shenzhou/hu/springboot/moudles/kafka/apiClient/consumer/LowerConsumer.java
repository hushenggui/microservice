package com.shenzhou.hu.springboot.moudles.kafka.apiClient.consumer;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.javaapi.message.MessageSet;
import kafka.message.MessageAndOffset;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: ideaSpaceMy
 * @description: 低级api
 * @author: hushenggui
 * @create: 2020-03-12 17:01
 **/
@Slf4j
public class LowerConsumer {

    public static void main(String[] args) {
        //定义相关参数
        ArrayList<String>  arrayList = new ArrayList<>();
        arrayList.add("hadoop101");
        arrayList.add("hadoop102");
        arrayList.add("hadoop103");
        //端口号
        int port = 9092;
        //主题
        String  topic = "主题";
        //分区
        int partition = 0;
        //offset
        long offset = 0;


    }


    /**
     * @Description: 找分区leader
     * @Param:
     * @return:
     * @Author: hushenggui
     * @Date:
     */
    public BrokerEndPoint findLeader(List<String> brokers, int port, String topic, int partition){
        for (String broker: brokers) {
            //创建获取分区leader的消费者对象
            SimpleConsumer getLeader = new SimpleConsumer(broker,port,1000,1024 * 4,"getLeader");
            //创建一个主题元数据信息请求  也可以传多个topic
            TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Collections.singletonList(topic));
            //获取主题元数据返回值
            TopicMetadataResponse topicMetadataResponse = getLeader.send(topicMetadataRequest);
            //解析元数据返回值
            List<TopicMetadata> topicsMetadata = topicMetadataResponse.topicsMetadata();
            //遍历主题元数据
            for (TopicMetadata topicMetadata : topicsMetadata){
                //主题
                topicMetadata.topic();
                //获取多个分区的元数据信息
                List<PartitionMetadata> partitionsMetadata = topicMetadata.partitionsMetadata();
                for (PartitionMetadata partitionMetadata : partitionsMetadata){
                    partitionMetadata.leader();
                    //分区id
                    int partitionId = partitionMetadata.partitionId();
                    //isr  副本的地方
                    partitionMetadata.isr();
                    if(partition == partitionId){
                        return  partitionMetadata.leader();
                    }
                }
            }
        }
        return null;
    }
    /**
     * @Description: 获取数据
     * @Param:
     * @return:
     * @Author: hushenggui
     * @Date:
     */
    public void getData(List<String> brokers, int port, String topic, int partition,long offset){
        //获取分区leader
        BrokerEndPoint leader =  findLeader(brokers,port,topic,partition);
        //
        String leaderHost = leader.host();
        //获取数据的消费者对象
        SimpleConsumer getData = new SimpleConsumer(leaderHost,port,1000,1024 * 4,"getData");
        //创建获取数据对象
        FetchRequest fetchRequest = new FetchRequestBuilder().addFetch(topic, partition, offset, 100).build();
        //获取数据返回值
        FetchResponse fetchResponse = getData.fetch(fetchRequest);
        //解析返回值
        ByteBufferMessageSet messageAndOffsets = fetchResponse.messageSet(topic, partition);

        for (MessageAndOffset  messageAndOffset : messageAndOffsets){
            long offset1 = messageAndOffset.offset();
            ByteBuffer byteBuffer = messageAndOffset.message().buffer();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            log.info("消息实体：" + new String(bytes));
        }
    }

}
