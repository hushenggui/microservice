package tuacy.microservice.framweork.bootqueue.rabbitmq.listener;

import com.rabbitmq.client.Recoverable;
import com.rabbitmq.client.RecoveryListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: microservice-framework
 * @description: 监听自动重连的情况，这个listener没有测试出在什么场景会出现
 * @author: hushenggui
 * @create: 2020-07-24 16:19
 **/
@Slf4j
@Service
public class RabbitRecoveryListener implements RecoveryListener {
    @Override
    public void handleRecovery(Recoverable recoverable) {
        log.info("================handleRecovery: {}", recoverable);
    }

    @Override
    public void handleRecoveryStarted(Recoverable recoverable) {
        log.info("================handleRecoveryStarted: {}", recoverable);
    }
}

