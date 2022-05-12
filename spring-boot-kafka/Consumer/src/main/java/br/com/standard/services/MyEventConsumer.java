package br.com.standard.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyEventConsumer {

    @Value(value = "springTopic")
    private String topic;

    @KafkaListener(topics = "springTopic", groupId = "default")
    public void consumer(ConsumerRecord<String, String> record) {
        System.out.println("topico: " + topic + " ,key: " + record.key() + " ,header: " + record.headers()
                + " ,particao: " + record.partition() + " ,ordem: " + record.value());
    }

}