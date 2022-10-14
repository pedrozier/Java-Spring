package com.standart.hpptconnection.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@RestController("/")
@AllArgsConstructor
public class MainController {

    private final RestTemplate restTemplate;
    private Environment environment;

    @PostMapping
    public String test(@RequestBody String text) {
        restTemplate.postForObject("http://localhost:8080/", text, String.class);
        return "Sent: " + text + " . from port: " + environment.getProperty("server.port") + " to port: 8080";
    }

}