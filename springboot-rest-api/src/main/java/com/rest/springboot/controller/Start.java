package com.rest.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Start {
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }



}
