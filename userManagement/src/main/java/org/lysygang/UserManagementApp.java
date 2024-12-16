package org.lysygang;

import org.lysygang.config.BatchProcessingConfiguration;
import org.lysygang.config.HttpRequestLoggingConfiguration;
import org.lysygang.config.ServiceConfig;
import org.lysygang.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({HttpRequestLoggingConfiguration.class,
        ServiceConfig.class,
        BatchProcessingConfiguration.class,
        WebConfig.class})
public class UserManagementApp {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApp.class, args);
    }
}