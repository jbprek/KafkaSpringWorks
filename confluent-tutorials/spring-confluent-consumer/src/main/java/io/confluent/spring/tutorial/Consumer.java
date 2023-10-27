package io.confluent.spring.tutorial;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
class Consumer {
    @KafkaListener(topics= {"hobbit"}, groupId="spring-boot-kafka")
    public void consume(String quote) {
        System.out.println("received= " + quote);
    }
}