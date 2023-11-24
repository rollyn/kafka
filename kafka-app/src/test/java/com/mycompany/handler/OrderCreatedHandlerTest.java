package com.mycompany.handler;

import com.mycompany.message.OrderCreated;
import com.mycompany.service.DispatchOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.Acknowledgment;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderCreatedHandlerTest {

    private OrderCreatedHandler handler;
    private DispatchOrderService service;

    private Acknowledgment acknowledgment;

    @BeforeEach
    void setUp() {
        service = mock(DispatchOrderService.class);
        handler = new OrderCreatedHandler(service);
        acknowledgment = mock(Acknowledgment.class);
    }

    @Test
    void listen() {
        OrderCreated order = OrderCreated.builder()
                .id(UUID.randomUUID())
                .item("Soda")
                .build();

        handler.listen(order, acknowledgment);
        verify(service, times(1)).process(order);
    }
}