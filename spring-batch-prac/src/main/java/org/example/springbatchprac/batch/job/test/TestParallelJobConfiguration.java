package org.example.springbatchprac.batch.job.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class TestParallelJobConfiguration {
    @Bean
    public Job parallelJob(
        JobRepository jobRepository,
        JobExecutionListener listener,
        Flow parallelFlow1,
        Flow parallelFlow2
    ) {
        return new JobBuilder("parallelJob", jobRepository)
            .start(parallelFlow1)
            .split(new SimpleAsyncTaskExecutor())
            .add(parallelFlow2)
            .end()
            .listener(listener)
            .build();
    }

    @Bean
    public Flow parallelFlow1(
        Step parallelStep1,
        Step parallelStep2
    ) {
        return new FlowBuilder<Flow>("parallelFlow1")
            .start(parallelStep1)
            .next(parallelStep2)
            .build();
    }

    @Bean
    public Flow parallelFlow2(
        Step parallelStep3
    ) {
        return new FlowBuilder<Flow>("parallelFlow2")
            .start(parallelStep3)
            .build();
    }

    @Bean
    public Step parallelStep1(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        Tasklet parallelTasklet1
    ) {
        return new StepBuilder("parallelStep1", jobRepository)
            .tasklet(parallelTasklet1, transactionManager)
            .build();
    }

    @Bean
    public Step parallelStep2(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        Tasklet parallelTasklet2
    ) {
        return new StepBuilder("parallelStep2", jobRepository)
            .tasklet(parallelTasklet2, transactionManager)
            .build();
    }

    @Bean
    public Step parallelStep3(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        Tasklet parallelTasklet3
    ) {
        return new StepBuilder("parallelStep3", jobRepository)
            .tasklet(parallelTasklet3, transactionManager)
            .build();
    }

    @Bean
    public Tasklet parallelTasklet1() {
        return (contribution, chunkContext) -> {
            System.out.println("parallelTasklet1 was executed");
            return null;
        };
    }

    @Bean
    public Tasklet parallelTasklet2() {
        return (contribution, chunkContext) -> {
            System.out.println("parallelTasklet2 was executed");
            return null;
        };
    }

    @Bean
    public Tasklet parallelTasklet3() {
        return (contribution, chunkContext) -> {
            System.out.println("parallelTasklet3 was executed");
            return null;
        };
    }
}
