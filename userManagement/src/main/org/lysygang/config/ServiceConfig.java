package org.lysygang.config;

import org.lysygang.application.domain.service.AddStudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AddStudentService addStudentService(){
        return new AddStudentService();
    }

}
