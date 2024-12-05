package org.lysygang.config;

import lombok.extern.slf4j.Slf4j;
import org.lysygang.application.domain.model.Student;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class StudentConverter implements ItemProcessor<Student, org.lysygang.adapter.out.persistence.entity.Student> {

    @Override
    public org.lysygang.adapter.out.persistence.entity.Student process(Student item) throws Exception {
        log.info("Converting student: {}", item);
        org.lysygang.adapter.out.persistence.entity.Student studentEntity = new org.lysygang.adapter.out.persistence.entity.Student();
        studentEntity.setFirstName(item.firstName());
        studentEntity.setLastName(item.lastName());
        return studentEntity;
    }

}
