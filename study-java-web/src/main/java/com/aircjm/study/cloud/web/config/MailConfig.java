package com.aircjm.study.cloud.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "mail"
)
@Data
public class MailConfig {


    private String sendEmailResultUrl;
    private String senderEmail;
    private String subject;



    private List<String> cc;
}
