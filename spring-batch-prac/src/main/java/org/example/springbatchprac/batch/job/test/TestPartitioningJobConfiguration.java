package org.example.springbatchprac.batch.job.test;

import jakarta.persistence.EntityManagerFactory;
import org.example.springbatchprac.batch.job.test.domain.TestEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.DefaultBufferedReaderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.batch.item.support.builder.SynchronizedItemStreamReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class TestPartitioningJobConfiguration {

    @Bean
    public Job partitioningJob(
        JobRepository jobRepository,
        JobExecutionListener listener,
        Step partitioningStep
    ) {
        return new JobBuilder("partitioningJob", jobRepository)
            .start(partitioningStep)
            .listener(listener)
            .build();
    }

    @Bean
    public Step partitioningStep(
        JobRepository jobRepository,
        Step testPagingStep,
        TestPartitioner testPartitioner,
        PartitionHandler testPartitionHandler,
        StepExecutionListener listener
    ) {
        return new StepBuilder("partitioningStep", jobRepository)
            .partitioner(testPagingStep.getName(), testPartitioner)
            .partitionHandler(testPartitionHandler)
            .listener(listener)
            .allowStartIfComplete(true)
            .build();
    }

    @Bean
    @Primary
    public TaskExecutorPartitionHandler testPartitionHandler(
        TaskExecutor taskExecutor,
        Step testPagingStep
    ) {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setTaskExecutor(taskExecutor);
        handler.setStep(testPagingStep);
        handler.setGridSize(5);
        System.out.println("=============== testPartitionHandler INITIALIZED ===============");
        return handler;
    }

    @Bean
    public Step testPagingStep(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        ItemReader<String> testPartitioningReader,
        ItemProcessor<String, TestEntity> testPartitioningProcessor,
        JpaItemWriter<TestEntity> testPartitioningWriter,
        TaskExecutor taskExecutor,
        StepExecutionListener listener
    ) {
        System.out.println("=============== testPagingStep START ===============");
        return new StepBuilder("testPagingStep", jobRepository)
            .<String, TestEntity>chunk(20, transactionManager)
            .reader(testPartitioningReader)
            .processor(testPartitioningProcessor)
            .writer(testPartitioningWriter)
            .listener(listener)
            .taskExecutor(taskExecutor)
            .build();
    }

    @Bean
    @StepScope
    public SynchronizedItemStreamReader<String> testPartitioningReader(
        @Value("#{stepExecutionContext['startLine']}") int startLine,
        @Value("#{stepExecutionContext['endLine']}") int endLine
    ) throws Exception {
        System.out.println("=============== testPartitioningReader ===============");
        System.out.println("startLine = " + startLine);
        System.out.println("endLine = " + endLine);

        FlatFileItemReader<String> reader = new FlatFileItemReaderBuilder<String>()
            .name("testPartitioningReader")
            .resource(new ClassPathResource("data/test.txt"))
            .linesToSkip(startLine - 1)
            .lineMapper((line, lineNumber) -> line)
            .maxItemCount(endLine - startLine + 1)
            .bufferedReaderFactory(new DefaultBufferedReaderFactory())
            .build();
        return new SynchronizedItemStreamReaderBuilder<String>()
            .delegate(reader)
            .build();

    }

    @Bean
    public ItemProcessor<String, TestEntity> testPartitioningProcessor() {
        System.out.println("===================== testPartitioningProcessor =====================");
        return item -> {
            String name = item.split(" ")[0];
            Long id = Long.parseLong(item.split(" ")[1]);
            return new TestEntity(id, name);
        };
    }

    @Bean
    public JpaItemWriter<TestEntity> testPartitioningWriter(
        EntityManagerFactory entityManagerFactory) {
        System.out.println("===================== testPartitioningWriter =====================");
        return new JpaItemWriterBuilder<TestEntity>()
            .entityManagerFactory(entityManagerFactory)
            .usePersist(true)
            .build();
    }
}
