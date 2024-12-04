package org.lysygang;

import org.lysygang.config.BatchProcessingConfiguration;
import org.lysygang.config.HttpRequestLoggingConfiguration;
import org.lysygang.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({HttpRequestLoggingConfiguration.class,
        ServiceConfig.class,
        BatchProcessingConfiguration.class})
public class UserManagementApp {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApp.class, args);
    }
}