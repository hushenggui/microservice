package tuacy.microservice.framweork.bootqueue.rabbitmq.listener;

import com.rabbitmq.client.ShutdownSignalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

/**
 * @program: microservice-framework
 * @description: 用于监听连接的创建和关闭
 * @author: hushenggui
 * @create: 2020-07-24 16:16
 **/
@Slf4j
@Service
public class RabbitConnectionListener implements ConnectionListener {
    @Override
    public void onCreate(Connection connection) {
        log.info("================onCreate: {}", connection);
    }

    @Override
    public void onClose(Connection connection) {
        log.info("================onClose: {}", connection);
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        log.info("================onShutDown: {}", signal);
    }
}

