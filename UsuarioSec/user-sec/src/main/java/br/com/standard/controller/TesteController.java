package br.com.standard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @RequestMapping({ "/hello" })
    public String hello() {
        return "Hello World";
    }

}
