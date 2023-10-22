package com.foo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class HelloConsumerApplication {


    @Value("#{'${spring.kafka.topics}'.split(',')}")
    private String[] topics;

    @KafkaListener(id="myId", topics = "#{'${spring.kafka.topics}'.split(',')}")
    public void listen(String in) {
       log.info(in);
    }

    @PostConstruct
    public void postConstruct() {
        log.info(topics.toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloConsumerApplication.class, args);
    }

}
