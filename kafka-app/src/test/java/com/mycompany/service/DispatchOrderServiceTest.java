package com.mycompany.service;

import com.mycompany.handler.OrderCreatedHandler;
import com.mycompany.message.OrderCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DispatchOrderServiceTest {

    private DispatchOrderService service;

    @BeforeEach
    void setUp() {
        service = new DispatchOrderService();
    }

    @Test
    void process() {
        OrderCreated order = OrderCreated.builder()
                .id(UUID.randomUUID())
                .item("Soda")
                .build();
        service.process(order);
    }
}