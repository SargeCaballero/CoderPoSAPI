package com.sargecaballero.coderproject.config;

import com.sargecaballero.coderproject.externalservice.UTCTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UTCTimeConfig {

    @Value("${world.time.api.url}")
    private String worldTimeApiUrl;

    @Bean
    public UTCTime utcTime(RestTemplate restTemplate) {
        return new UTCTime(restTemplate, worldTimeApiUrl);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}