package org.lysygang.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.lysygang.application.port.in.AddStudentByBatchLauncher;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
public class AddStudentJobLauncher implements AddStudentByBatchLauncher {

    private final JobLauncher jobLauncher;
    private final Job job;

    public void runJob(MultipartFile file) throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        // Zapisz plik tymczasowo lub przekaż jego ścieżkę jako parametr
        String tempFilePath = STR."/tmp/\{file.getOriginalFilename()}";
        file.transferTo(new java.io.File(tempFilePath));

        // Uruchom job Spring Batch z parametrem ścieżki do pliku
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("filePath", tempFilePath)
                .addLong("startTime", System.currentTimeMillis()) // Unikalny parametr dla uniknięcia problemów z cache
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}
