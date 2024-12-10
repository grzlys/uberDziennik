package org.lysygang.config;

import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.application.domain.model.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchProcessingConfiguration {

    @Bean
    public FlatFileItemReader<Student> reader() {
        return new FlatFileItemReaderBuilder<Student>()
                .name("studentItemReader")
                .resource(new ClassPathResource("input.csv"))
                .delimited()
                .names("id","firstName", "lastName")
                .targetType(Student.class) // todo replace to AddStudentCommand, it can help with no id in file
                .build();
    }

    @Bean
    public StudentConverter studentConverter() {
        return new StudentConverter();
    }

    @Bean
    public ItemWriter<org.lysygang.adapter.out.persistence.entity.Student> writer(StudentPersistenceAdapter studentPersistenceAdapter) {
        return new CustomStudentItemWriter(studentPersistenceAdapter);
    }

    // job definition

    @Bean
    public Job importStudentJob(JobRepository jobRepository, Step importStudentStep) {
        return new JobBuilder("importStudentJob", jobRepository)
                .start(importStudentStep)
                .build();
    }

    @Bean
    public Step importStudentStep(JobRepository jobRepository,
                                  ItemReader<Student> reader,
                                  ItemProcessor<? super Student, ? extends org.lysygang.adapter.out.persistence.entity.Student> studentConverter,
                                  ItemWriter<org.lysygang.adapter.out.persistence.entity.Student> writer,
                                  PlatformTransactionManager transactionManager) {
        return new StepBuilder("importStudentStep", jobRepository)
                .<Student, org.lysygang.adapter.out.persistence.entity.Student>chunk(3, transactionManager)
                .reader(reader)
                .processor(studentConverter)
                .writer(writer)
                .allowStartIfComplete(true) // maybe wiping out batch job tables can make to delete this line of code
                .build();
    }

}
