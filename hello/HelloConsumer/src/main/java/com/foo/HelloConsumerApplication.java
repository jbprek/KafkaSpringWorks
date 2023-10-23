package com.foo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class HelloConsumerApplication {

    @KafkaListener(topics = "#{'${spring.kafka.topics}'.split(',')}")
    public void listen(String in) {
       log.info("### {}",in);
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloConsumerApplication.class, args);
    }

}
