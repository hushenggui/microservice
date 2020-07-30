package tuacy.microservice.framweork.bootqueue.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @program: microservice-framework
 * @description: kafka简单生产者
 * @author: hushenggui
 * @create: 2020-07-29 11:10
 **/
@Service
@Slf4j
public class KafkaSimpleProducerService {
    @Autowired
    KafkaTemplate kafkaTemplate;

    /**
    * @Description:
    * @Param: []
    * @return:
    * @Author: hushenggui
    * @Date: 2020/7/29
    */
    public void sendSimple(){
        try {
            log.info("logVo {}", "开始发送");
            ListenableFuture future = kafkaTemplate
                    .send("first", "hsg", "first--ceshi01");
            future.addCallback(success -> {
                log.info("success");
            }, fail -> {
                log.info("fail");
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
