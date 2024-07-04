package org.lysygang;

import org.lysygang.config.HttpRequestLoggingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(HttpRequestLoggingConfiguration.class)
public class UserManagementApp {
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApp.class, args);
    }
}