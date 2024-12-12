package org.lysygang.config;

import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.FileSystemResource;

public class FileReaderListener {

    private final FlatFileItemReader<AddStudentCommand> reader;

    public FileReaderListener(FlatFileItemReader<AddStudentCommand> reader) {
        this.reader = reader;
    }

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        var filePath = stepExecution.getJobParameters().getString("filePath");
        reader.setResource(new FileSystemResource(filePath));
    }
}
