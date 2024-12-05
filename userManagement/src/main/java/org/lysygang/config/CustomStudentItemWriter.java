package org.lysygang.config;

import lombok.extern.slf4j.Slf4j;
import org.lysygang.adapter.out.persistence.entity.Student;
import org.lysygang.adapter.out.persistence.repository.StudentRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class CustomStudentItemWriter implements ItemWriter<Student> {

    private final StudentRepository studentRepository;

    public CustomStudentItemWriter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void write(Chunk<? extends Student> chunk) throws Exception {
        for (Student student : chunk.getItems()) {
            log.info("Saving student: {}", student);
            studentRepository.save(student);
        }
    }
}
