package tuacy.microservice.framweork.bootqueue.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @program: microservice-framework
 * @description: kafka简单消费者
 * @author: hushenggui
 * @create: 2020-07-29 11:09
 **/
@Component
@Slf4j
public class KafkaSimpleConsumeService {
    @KafkaListener(topics = "first",groupId = "cuntomer1")
    public void consumer(ConsumerRecord consumerRecord){
        log.info(consumerRecord.toString());
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMassage.isPresent()){
            Object o = kafkaMassage.get();
            log.info("kafka consumer11111111 message {}",o);
        }
    }


    @KafkaListener(topics = "first",groupId = "cuntomer2")
    public void consumer1(ConsumerRecord consumerRecord){
        log.info(consumerRecord.toString());
        Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMassage.isPresent()){
            Object o = kafkaMassage.get();
            log.info("kafka consumer2222222 message {}",o);
        }
    }
}
