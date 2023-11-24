package com.mycompany.handler;

import com.mycompany.message.OrderCreated;
import com.mycompany.service.DispatchOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.net.SocketTimeoutException;

@RequiredArgsConstructor
@Slf4j
@Component
@KafkaListener(
        id="orderConsumerClient",
        topics="order.created",
        properties = {"enable.auto.commit:false", "auto.offset.reset:latest"},
        groupId = "dispatch.order.created.consumer"
)
public class OrderCreatedHandler {

    private final DispatchOrderService orderService;


    @KafkaHandler
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts = "2",
            autoCreateTopics = "false",
            include = SocketTimeoutException.class, exclude = NullPointerException.class)
    @DltHandler public void listen(OrderCreated order, Acknowledgment acknowledgment) {
        orderService.process(order);
//        throw new NullPointerException();
        acknowledgment.acknowledge();

    }
}
