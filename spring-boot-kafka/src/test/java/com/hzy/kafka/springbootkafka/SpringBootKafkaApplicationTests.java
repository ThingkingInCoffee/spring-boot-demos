package com.hzy.kafka.springbootkafka;

import com.hzy.kafka.SpringBootKafkaApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootKafkaApplication.class)
class SpringBootKafkaApplicationTests {

    @Test
    void contextLoads() {
    }

}
