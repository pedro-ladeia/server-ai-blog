package com.aiblog.aiblog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TesteController {

    @GetMapping("/posts")
    public String teste() {
        return "End point funcionando com sucesso";
    }
}
