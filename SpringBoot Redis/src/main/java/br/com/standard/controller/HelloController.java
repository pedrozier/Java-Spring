package br.com.standard.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloworld")
    @Cacheable("hello")
    public String hello() {
        System.out.println("cache salvo");
        return "Hello World";
    }

    @GetMapping("/helloworld2")
    @CacheEvict("hello")
    public String hello2() {
        System.out.println("cache limpo");
        return "Hello World";
    }
}
