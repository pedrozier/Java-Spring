package br.com.standard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaMessageController {

    @GetMapping("/getMessage")
    public ResponseEntity listenerGroupDefault(String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
