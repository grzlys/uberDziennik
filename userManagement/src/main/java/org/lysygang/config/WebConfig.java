package org.lysygang.config;

import org.lysygang.adapter.in.web.AddStudentController;
import org.lysygang.application.domain.service.AddStudentJobLauncher;
import org.lysygang.application.domain.service.AddStudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public AddStudentController addStudentController(AddStudentService addStudentService, AddStudentJobLauncher addstudentJobLauncher) {
        return new AddStudentController(addStudentService, addstudentJobLauncher);
    }


}
