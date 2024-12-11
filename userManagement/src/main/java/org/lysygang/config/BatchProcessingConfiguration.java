package org.lysygang.config;

import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
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
    public FlatFileItemReader<AddStudentCommand> reader() {
        return new FlatFileItemReaderBuilder<AddStudentCommand>()
                .name("studentItemReader")
                .resource(new ClassPathResource("input.csv"))
                .delimited()
                .names("firstName", "lastName")
                .targetType(AddStudentCommand.class)
                .build();
    }

    @Bean
    public ItemWriter<AddStudentCommand> writer(StudentPersistenceAdapter studentPersistenceAdapter) {
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
                                  ItemReader<AddStudentCommand> reader,
                                  ItemWriter<AddStudentCommand> writer,
                                  PlatformTransactionManager transactionManager) {
        return new StepBuilder("importStudentStep", jobRepository)
                .<AddStudentCommand, AddStudentCommand>chunk(3, transactionManager)
                .reader(reader)
                .writer(writer)
                .allowStartIfComplete(true) // maybe wiping out batch job tables can make to delete this line of code
                .build();
    }

}
