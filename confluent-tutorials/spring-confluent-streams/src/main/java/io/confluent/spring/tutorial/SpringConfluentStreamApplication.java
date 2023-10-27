package io.confluent.spring.tutorial;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;


@SpringBootApplication
@EnableKafkaStreams
public class SpringConfluentStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfluentStreamApplication.class, args);
    }


    @Bean
    public KafkaAdmin admin() {
        return new KafkaAdmin(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"));
    }

    @Bean
    NewTopic counts() {
        return TopicBuilder.name("streams-wordcount-output").partitions(6).replicas(3).build();
    }

}
