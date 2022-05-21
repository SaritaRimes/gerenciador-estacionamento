package com.saritarimes.gerenciadorestacionamento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {
    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String HelloWorld() {
        return "Hello, World!";
    }
}
