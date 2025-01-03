package org.example.springbatchprac.batch.job.api;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SendJobConfiguration {
    @Bean
    public Job apiJob(
        JobRepository jobRepository,
        JobExecutionListener listener,
        Step apiStep1,
        Step jobStep,
        Step apiStep2
    ) {
        return new JobBuilder("apiJob", jobRepository)
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .start(apiStep1)
            .next(jobStep)
            .next(apiStep2)
            .build();
    }

    @Bean
    public Step apiStep1(
        JobRepository jobRepository,
        Tasklet apiStartTasklet,
        PlatformTransactionManager transactionManager
    ) {
        return new StepBuilder("apiStep1", jobRepository)
            .tasklet(apiStartTasklet, transactionManager)
            .build();
    }

    @Bean
    public Step apiStep2(
        JobRepository jobRepository,
        Tasklet apiEndTasklet,
        PlatformTransactionManager transactionManager
    ) {
        return new StepBuilder("apiStep2", jobRepository)
            .tasklet(apiEndTasklet, transactionManager)
            .build();
    }

    @Bean
    public Tasklet apiStartTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("");
            System.out.println(">> ApiStartTasklet is started");
            System.out.println("");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Tasklet apiEndTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("");
            System.out.println(">> ApiEndTasklet is started");
            System.out.println("");
            System.out.println("******************************************************************************************************************************************************");
            System.out.println("*                                                               Spring Batch is completed                                                            *");
            System.out.println("******************************************************************************************************************************************************");
            System.out.println("");
            return RepeatStatus.FINISHED;
        };
    }
}
