package tuacy.microservice.framweork.bootqueue.kafka.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: microservice-framework
 * @description: kafka配置类
 * @author: hushenggui
 * @create: 2020-07-30 16:04
 **/

@Configuration
public class KafkaInitialConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    //创建TopicName为topic.quick.initial的Topic并设置分区数为8以及副本数为1
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic("simple-topic1",1, (short) 1 );
    }


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> props = new HashMap<>();
        //配置Kafka实例的连接地址
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        KafkaAdmin admin = new KafkaAdmin(props);
        return admin;
    }

    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }
}
