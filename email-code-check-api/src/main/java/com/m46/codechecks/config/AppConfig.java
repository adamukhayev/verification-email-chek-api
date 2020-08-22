package com.m46.codechecks.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class AppConfig {

    private EmailCodeCheckProps emailCodeCheckProps;

    @Bean
    public RestTemplate hubSpotRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
