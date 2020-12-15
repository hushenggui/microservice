package tuacy.microservice.framweork;
import com.google.gson.Gson;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tuacy.microservice.framweork.bootqueue.BootQueueApplication;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * @program: microservice-framework
 * @description: kafka测试类
 * @author: hushenggui
 * @create: 2020-07-30 16:47
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootQueueApplication.class)
public class KafkaTest {

    @Autowired
    private AdminClient adminClient;
    Gson gson = new Gson();

    @Test
    public void testSelectTopicInfo() throws ExecutionException, InterruptedException {
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList("first"));
        result.all().get().forEach((k,v)->System.out.println("k: "+k+" ,v: "+v.toString()+"\n"));
    }

    @Test
    public void testPass(){


    }


}
