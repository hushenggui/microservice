package tuacy.microservice.framweork.bootqueue.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.impl.AMQImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.stereotype.Service;

/**
 * @program: microservice-framework
 * @description: 用于监听通道的创建和销毁
 * @author: hushenggui
 * @create: 2020-07-24 16:17
 **/
@Slf4j
@Service
public class RabbitChannelListener implements ChannelListener {
    @Override
    public void onCreate(Channel channel, boolean b) {
        log.info("======================onCreate channel: {}, transactional: {}", channel, b);
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        // 可根据isHardError判断是channel断开还是connection断开
        if (signal.isHardError()) {
            AMQImpl.Connection.Close close = (AMQImpl.Connection.Close) signal.getReason();
            log.warn("=====================Connection onShutDown replyCode: {}, methodId: {}, classId: {}, replyText: {}",
                    close.getReplyCode(), close.getMethodId(), close.getClassId(), close.getReplyText());
        } else {
            AMQImpl.Channel.Close close = (AMQImpl.Channel.Close) signal.getReason();
            log.warn("=====================Channel onShutDown replyCode: {}, methodId: {}, classId: {}, replyText: {}",
                    close.getReplyCode(), close.getMethodId(), close.getClassId(), close.getReplyText());
        }
    }
}

