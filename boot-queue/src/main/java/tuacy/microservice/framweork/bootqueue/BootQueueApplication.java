package tuacy.microservice.framweork.bootqueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableRabbit
@EnableEurekaClient
@Slf4j
@EnableFeignClients(basePackages = "com.cngongbao")
@ComponentScan(basePackages = { "com.cngongbao", "tuacy.microservice"})
public class BootQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootQueueApplication.class, args);
    }

}
