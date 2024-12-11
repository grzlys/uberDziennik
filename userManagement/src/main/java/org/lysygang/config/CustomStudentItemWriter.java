package org.lysygang.config;

import lombok.extern.slf4j.Slf4j;
import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class CustomStudentItemWriter implements ItemWriter<AddStudentCommand> {

    private final StudentPersistenceAdapter studentPersistenceAdapter;

    public CustomStudentItemWriter(StudentPersistenceAdapter studentPersistenceAdapter) {
        this.studentPersistenceAdapter = studentPersistenceAdapter;
    }

    @Override
    public void write(Chunk<? extends AddStudentCommand> chunk) throws Exception {
        for (AddStudentCommand addStudentCommand : chunk.getItems()) {
            log.info("Saving student: {}", addStudentCommand);
            studentPersistenceAdapter.saveStudent(addStudentCommand);
        }
    }
}
