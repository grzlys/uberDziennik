package org.lysygang.config;

import lombok.extern.slf4j.Slf4j;
import org.lysygang.adapter.out.persistence.entity.Student;
import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class CustomStudentItemWriter implements ItemWriter<Student> {

    private final StudentPersistenceAdapter studentPersistenceAdapter;

    public CustomStudentItemWriter(StudentPersistenceAdapter studentPersistenceAdapter) {
        this.studentPersistenceAdapter = studentPersistenceAdapter;
    }

    @Override
    public void write(Chunk<? extends Student> chunk) throws Exception {
        for (Student student : chunk.getItems()) {
            log.info("Saving student: {}, {}, {}", student.getId() ,student.getFirstName(), student.getLastName());
            studentPersistenceAdapter.saveStudent(new AddStudentCommand(student.getFirstName(), student.getLastName()));
        }
    }
}
