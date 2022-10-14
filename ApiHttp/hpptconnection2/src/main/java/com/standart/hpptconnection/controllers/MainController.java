package com.standart.hpptconnection.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MainController {

    @PostMapping
    public String test(@RequestBody String text) {
        System.out.println("ok");
        return "ok";
    }

}