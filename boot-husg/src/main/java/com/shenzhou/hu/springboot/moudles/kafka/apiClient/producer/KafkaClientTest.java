package com.shenzhou.hu.springboot.moudles.kafka.apiClient.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * kafka生产者API的使用
 */
@Slf4j
public class KafkaClientTest {
    /**
     * @Description: kafka生产者API的使用
     * @Param:
     * @return:
     * @Author: hushenggui
     * @Date:
     */
    public static void main(String[] args) {
        // ProducerConfig  配置参考这个类
        //
        //配置信息
        Properties props = new Properties();
        //kafka集群
        props.put("bootstrap.servers", "localhost:9092");
        //ack机制
        props.put("acks", "all");
        //重试次数
        props.put("retries", 0);
        //批量大小
        props.put("batch.size", 16384);
        //提交延时
        props.put("linger.ms", 1);
        //整个生产者缓存
        props.put("buffer.memory", 33554432);
        //KV序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //自定义分区
        props.put("partitioner.class","com.shenzhou.hu.springboot.moudles.kafka.apiClient.producer.CustomPartitioner");

        //创建生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(props);
        //循环发送数据
        for (int i = 0; i < 10; i++) {
            //topic  名称 分区  key  value
            ProducerRecord<String,String> record = new ProducerRecord<String, String>("first",1,String.valueOf(i),String.valueOf(i));
            producer.send(record);

            //发送回调函数的record  只有一个接口  函数式接口可以用lamda
            Callback callback = new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    String topic = metadata.topic();
                    long offset = metadata.offset();
                    int partition = metadata.partition();
                    //非正常发送
                    if(exception == null){

                    }else {
                        log.info("发送失败");
                    }
                }
            };
            ProducerRecord<String,String> recordCall = new ProducerRecord<String, String>("first",1,String.valueOf(i),String.valueOf(i));
            //producer.send(recordCall,callback);

            //lamda 表达式写法
            producer.send(recordCall,(metadata,exception) ->{
                String topic = metadata.topic();
                long offset = metadata.offset();
                int partition = metadata.partition();
                //非正常发送
                if(exception == null){

                }else {
                    log.info("发送失败");
                }
            });
        }
        //关闭资源
        producer.close();

    }

}
