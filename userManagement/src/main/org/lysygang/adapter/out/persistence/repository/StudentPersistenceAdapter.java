package org.lysygang.adapter.out.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.lysygang.adapter.out.persistence.entity.Student;
import org.lysygang.application.port.in.AddStudentCommand;
import org.lysygang.application.port.out.SaveStudentPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@RequiredArgsConstructor
@Component
public class StudentPersistenceAdapter implements SaveStudentPort {

//    private final StudentRepository repository;
    @Autowired
    private StudentRepository repository;

    @Override
    public int saveStudent(AddStudentCommand command) {
        var student = new Student();
        student.setFirstName(command.firstName());
        student.setLastName(command.lastName());
        repository.save(student);
        return student.getId();
    }
}
