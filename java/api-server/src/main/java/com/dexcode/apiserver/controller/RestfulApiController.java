package com.dexcode.apiserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestfulApiController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

}
