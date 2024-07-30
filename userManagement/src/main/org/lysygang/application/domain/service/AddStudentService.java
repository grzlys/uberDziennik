package org.lysygang.application.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.application.port.in.AddStudent;
import org.lysygang.application.port.in.AddStudentCommand;

@Slf4j
@RequiredArgsConstructor
public class AddStudentService implements AddStudent {

    private final StudentPersistenceAdapter studentPersistenceAdapter;

    @Override
    public int addNewStudent(AddStudentCommand command) {
        log.info("Add new student command: {}", command);
        return studentPersistenceAdapter.saveStudent(command);
    }
}
