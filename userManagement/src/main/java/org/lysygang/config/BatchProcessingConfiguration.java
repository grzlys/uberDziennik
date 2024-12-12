package org.lysygang.config;

import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchProcessingConfiguration {


    @Bean
    public FlatFileItemReader<AddStudentCommand> restReader(){
        FlatFileItemReader<AddStudentCommand> reader = new FlatFileItemReader<>();
        skipHeaders(reader);

        DefaultLineMapper<AddStudentCommand> lineMapper = new DefaultLineMapper<>();
        lineMapper.setFieldSetMapper(fieldSet ->
                new AddStudentCommand(fieldSet.readString("firstName"), fieldSet.readString("lastName")));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("firstName", "lastName");

        lineMapper.setLineTokenizer(tokenizer);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    private static void skipHeaders(FlatFileItemReader<AddStudentCommand> reader) {
        reader.setLinesToSkip(1);
    }

    @Bean
    public ItemWriter<AddStudentCommand> writer(StudentPersistenceAdapter studentPersistenceAdapter) {
        return new CustomStudentItemWriter(studentPersistenceAdapter);
    }

    @Bean
    public Job importStudentJob(JobRepository jobRepository, Step importStudentStep) {
        return new JobBuilder("importStudentJob", jobRepository)
                .start(importStudentStep)
                .build();
    }

    @Bean
    public Step importStudentStep(JobRepository jobRepository,
                                  FlatFileItemReader<AddStudentCommand>restReader,
                                  ItemWriter<AddStudentCommand> writer,
                                  PlatformTransactionManager transactionManager) {
        return new StepBuilder("importStudentStep", jobRepository)
                .<AddStudentCommand, AddStudentCommand>chunk(3, transactionManager)
                .listener(new FileReaderListener(restReader))
                .reader(restReader)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }

}
