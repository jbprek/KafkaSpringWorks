package io.confluent.spring.tutorial;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

@Component
class Processor {
    /*
    use @Autowired on methods like these when you need to perform some setup or initialization logic that requires the injected dependencies. It's a way to signal to Spring that this method should be invoked automatically after the bean is created and dependencies are injected.
     */
    @Autowired
    public void process(StreamsBuilder builder) {

        //  Add SerDes as well as a KStream to specify the topic to read from
        final Serde<Integer> integerSerde = Serdes.Integer();
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();

        KStream<Integer, String> textLines = builder.stream("hobbit",
                Consumed.with(integerSerde, stringSerde));

        // add a KTable to process your streamed data, splitting each message into words with flatMapValues, grouping, and then counting
        KTable<String, Long> wordCounts = textLines
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
                .count();
    }

}