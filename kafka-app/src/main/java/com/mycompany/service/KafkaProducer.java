package com.mycompany.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.message.OrderCreated;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    @Value("${kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @SneakyThrows
    public void publish(OrderCreated order) {
        log.info("Topic name: {}",topic);
        kafkaTemplate.send(topic, order);
    }
}