package tuacy.microservice.framweork.bootqueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableRabbit
@EnableEurekaClient
@Slf4j
public class BootQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootQueueApplication.class, args);
    }

}
