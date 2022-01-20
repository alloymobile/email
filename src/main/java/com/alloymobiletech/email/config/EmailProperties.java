package com.alloymobiletech.email.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("email")
public class EmailProperties {
    private String siteURL;
    private String fromEmail;
    private String secret;
    private String tokenPrefix;
    private String headerString;
}
