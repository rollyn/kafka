package com.mycompany.controller;

import com.mycompany.message.OrderCreated;
import com.mycompany.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class DispatchController {

    private final KafkaProducer producer;

    @PostMapping
    public void send(@RequestBody OrderCreated order) {
        log.info("Publishing message..");
        producer.publish(order);
    }
}
