package com.ejemplo.customers.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvLogger {
    private static final Logger log = LoggerFactory.getLogger(EnvLogger.class);

    @Bean
    CommandLineRunner logEnv(@Value("${app.env.message}") String msg,
                             @Value("${spring.application.name}") String name) {
        return args -> log.info("[{}] {}", name, msg);
    }
}