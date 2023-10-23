package com.foo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/events")
@Slf4j
public class ApiController {

    private KafkaTemplate<Object, Object> template;

    @Value("${spring.kafka.topic}")
    private String topic;

    public ApiController(KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody String input) {
        log.info(Instant.now().toString());
        template.send(topic, input);
        return ResponseEntity.ok(input);
    }


}
