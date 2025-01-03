package org.example.springbatchprac.batch.job.api;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendChildJobConfiguration {

    @Bean
    public Step jobStep(
        JobRepository jobRepository,
        Job childJob,
        JobLauncher jobLauncher
    ) {
        return new StepBuilder("jobStep", jobRepository)
            .job(childJob)
            .launcher(jobLauncher)
            .build();
    }

    @Bean
    public Job childJob(
        JobRepository jobRepository,
        JobExecutionListener listener,
        Step apiMasterStep
    ) {
        return new JobBuilder("childJob", jobRepository)
            .start(apiMasterStep)
            .listener(listener)
            .build();
    }
}
