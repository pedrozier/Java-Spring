package br.com.standard.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonSerializable;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.standard.model.Entity;

@RestController
@RequestMapping("kafka")
public class KafkaMessageController {

    @Autowired
    private KafkaTemplate<String, Entity> kafkaTemplate;

    @Value(value = "springTopic")
    private String topic;

    @PostMapping(value = "/sendMessage", consumes = { "application/json" }, produces = { "application/json" })
    public ResponseEntity sendMessage(@RequestBody @Valid Entity message) {

        String key = UUID.randomUUID().toString();

        Entity entity = new Entity(message.getName(), message.getQuantity());

        ProducerRecord<String, Entity> record = new ProducerRecord<String, Entity>(topic, key, entity);

        ListenableFuture<SendResult<String, Entity>> future = kafkaTemplate.send(record);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Entity>>() {

            @Override
            public void onSuccess(SendResult<String, Entity> result) {
                System.out.println(
                        "Send message=[" + entity.toString() + "] with offset=[" + result.getRecordMetadata().offset()
                                + "]");

            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + entity.toString() + "] due to : " + ex.getMessage());

            }
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
