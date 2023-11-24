package com.mycompany.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.message.OrderCreated;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.io.UncheckedIOException;
import java.util.LinkedHashMap;

public class CustomDeserializer<T> implements Deserializer<T> {

    ObjectMapper mapper = new ObjectMapper();

    Deserializer<T> deserializer = new JsonDeserializer<>();

    @SuppressWarnings("unchecked")
    @SneakyThrows
    @Override
    public T deserialize(String topic, byte[] data) {

        System.out.println("Deserialize.............");
//        GenericMessage<T> message = mapper.readValue(data, GenericMessage.class);
        try {
//            return deserializer.deserialize(topic, data);

//            return (T) mapper.readValue(data, LinkedHashMap.class);
            return (T) mapper.readValue(data, OrderCreated.class);
        } catch (Exception e) {
            System.out.println("Alert Support.............");
            throw e;
        }
    }
}
