package org.lysygang.config;

import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.adapter.out.persistence.repository.StudentRepository;
import org.lysygang.application.domain.service.AddStudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

//    @Bean
//    public StudentPersistenceAdapter studentPersistenceAdapter(StudentRepository studentRepository) {
//        return new StudentPersistenceAdapter(studentRepository);
//    }

    @Bean
    public AddStudentService addStudentService(StudentPersistenceAdapter studentPersistenceAdapter){
        return new AddStudentService(studentPersistenceAdapter);
    }

}
