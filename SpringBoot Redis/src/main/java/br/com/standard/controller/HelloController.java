package br.com.standard.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloworld")
    @Cacheable("hello")
    public String hello() {
        System.out.println("ok");
        return "Hello World";
    }
}
