package com.lightspeed.incrementor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataConfiguration {
    @Bean
    public Map<String, Long> counterStorage() {
        return new HashMap<>();
    }
}
