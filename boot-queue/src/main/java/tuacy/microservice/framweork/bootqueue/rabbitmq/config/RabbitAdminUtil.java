package tuacy.microservice.framweork.bootqueue.rabbitmq.config;

import com.netflix.discovery.converters.jackson.EurekaXmlJacksonCodec;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @program: microservice-framework
 * @description: rabbitmq绑定类
 * @author: hushenggui
 * @create: 2020-07-24 14:54
 **/
@Component
public class RabbitAdminUtil {
    @Autowired
    RabbitAdmin rabbitAdmin;
    /**
     * 队列与交换机进行绑定
     *
     * @param queueName    队列名称
     * @param exchangeName 交换机名称
     * @param routingKey   路由键
     * @return
     */
    public boolean queueBind(String queueName, String exchangeName, String routingKey) {
        return this.bing(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);
    }

    /**
     * bind绑定
     *
     * @param destName     目标名称（可以是队列 也可以是交换机）
     * @param type         绑定的类型 交换机 / 队列
     * @param exchangeName 交换机的名称
     * @param routingKey   路由键
     * @param map          结构参数
     * @return
     */
    private  boolean bing(String destName, Binding.DestinationType type, String exchangeName, String routingKey, Map<String, Object> map) {
        Binding binding = new Binding(destName, type, exchangeName, routingKey, map);
        try {
            rabbitAdmin.declareBinding(binding);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 清空队列中的消息
     * @param queueName
     * @return 清楚队列中的消息的个数
     */
    public boolean purgeQueue(String queueName){
        if (!StringUtils.isEmpty(queueName)) {
            rabbitAdmin.purgeQueue(queueName,true);
            return true;
        }else{
            return false;
        }
    }
}
