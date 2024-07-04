package org.lysygang.application.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.lysygang.application.port.in.AddStudent;
import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddStudentService implements AddStudent {

    @Override
    public int addNewStudent(AddStudentCommand command) {
        log.info("Add new student command: {}", command);
        return 2137;
    }
}
