package br.com.standard.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.standard.model.Entity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyEventConsumer {

    @Value(value = "springTopic")
    private String topic;

    @KafkaListener(topics = "springTopic", groupId = "default")
    public void consumer(ConsumerRecord<String, Entity> record) {
        System.out.println("topic: " + topic + " ,key: " + record.key() + " ,header: " + record.headers()
                + " ,partition: " + record.partition() + " ,order: " + record.value());
    }

}