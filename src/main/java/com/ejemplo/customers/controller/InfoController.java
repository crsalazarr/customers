package com.ejemplo.customers.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @Value("${spring.application.name}")
    private String name;

    @Value("${app.env.message}")
    private String message;

    @Value("${server.port}")
    private String port;

    @GetMapping
    public Map<String, String> info() {
        return Map.of("name", name, "message", message, "port", port);
    }
}