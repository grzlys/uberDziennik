package org.lysygang.application.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

class AddStudentJobLauncherTest {

    @InjectMocks
    private AddStudentJobLauncher addstudentJobLauncher;

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job job;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDelegateJobToLauncher() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, IOException, JobParametersInvalidException, JobRestartException {
        //given
        var file = new MockMultipartFile("file", "input.cvs", "text/plain", "inputFile".getBytes());

        //when
        addstudentJobLauncher.runJob(file);

        // then
        Mockito.verify(jobLauncher, times(1)).run(eq(job), any(JobParameters.class));
    }
}