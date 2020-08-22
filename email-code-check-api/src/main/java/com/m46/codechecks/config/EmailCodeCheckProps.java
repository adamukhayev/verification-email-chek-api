package com.m46.codechecks.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "email-code-check-api")
public class EmailCodeCheckProps {

    @NotNull
    private Integer validityPeriod = 24;

    private Integer codeLength = 6;

    private Long emailTemplateId = 12345L;

    private String apiBaseUrl;
    private String apiKey;
}
