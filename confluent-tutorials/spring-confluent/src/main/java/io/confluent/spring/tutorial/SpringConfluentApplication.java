package io.confluent.spring.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
//@Configuration
public class SpringConfluentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfluentApplication.class, args);
    }



}
