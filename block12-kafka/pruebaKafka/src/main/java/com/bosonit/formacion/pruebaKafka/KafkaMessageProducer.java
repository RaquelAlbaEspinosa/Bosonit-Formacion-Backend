package com.bosonit.formacion.pruebaKafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaMessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    public void sendMessage(String topic,String message) {
        if (topic==null || topic.trim().equals(""))
            topic=topicName;
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.err.println("Unable to send message=[" + message + "] due to: " + ex.getMessage());
            }
        });
    }
}
