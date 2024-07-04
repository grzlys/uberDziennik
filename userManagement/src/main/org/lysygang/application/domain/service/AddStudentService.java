package org.lysygang.application.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lysygang.adapter.out.persistence.entity.Student;
import org.lysygang.adapter.out.persistence.repository.StudentRepository;
import org.lysygang.application.port.in.AddStudent;
import org.lysygang.application.port.in.AddStudentCommand;

@Slf4j
@RequiredArgsConstructor
public class AddStudentService implements AddStudent {

    private final StudentRepository repository;

    @Override
    public int addNewStudent(AddStudentCommand command) {
        log.info("Add new student command: {}", command);
        var student = new Student();
        student.setFirstName(command.firstName());
        student.setLastName(command.lastName());
        repository.save(student);
        return student.getId();
    }
}
