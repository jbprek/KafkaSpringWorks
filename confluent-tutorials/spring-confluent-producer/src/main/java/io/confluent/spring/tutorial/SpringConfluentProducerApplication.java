package io.confluent.spring.tutorial;

import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@SpringBootApplication
@EnableKafka
//@Configuration
public class SpringConfluentProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfluentProducerApplication.class, args);
    }


    @Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(
                Map.of(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                        RETRIES_CONFIG, 0,
                        BUFFER_MEMORY_CONFIG, 33554432,
                        KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class,
                        VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class
                ));
    }

    @Bean
    public KafkaTemplate<Integer, String> kafkaTemplate(ProducerFactory<Integer, String> producerFactory) {
        return new KafkaTemplate<Integer, String>(producerFactory);
    }

}
