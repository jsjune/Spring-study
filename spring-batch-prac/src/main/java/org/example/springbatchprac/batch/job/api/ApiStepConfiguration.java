package org.example.springbatchprac.batch.job.api;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.springbatchprac.batch.chunk.processor.ApiItemProcessor1;
import org.example.springbatchprac.batch.chunk.processor.ApiItemProcessor2;
import org.example.springbatchprac.batch.chunk.processor.ApiItemProcessor3;
import org.example.springbatchprac.batch.chunk.processor.ProcessorClassifier;
import org.example.springbatchprac.batch.chunk.wrtier.ApiItemWriter1;
import org.example.springbatchprac.batch.chunk.wrtier.ApiItemWriter2;
import org.example.springbatchprac.batch.chunk.wrtier.ApiItemWriter3;
import org.example.springbatchprac.batch.chunk.wrtier.WriterClassifier;
import org.example.springbatchprac.batch.domain.ApiRequestVO;
import org.example.springbatchprac.batch.domain.Product;
import org.example.springbatchprac.batch.domain.ProductVO;
import org.example.springbatchprac.batch.partition.ProductPartitioner;
import org.example.springbatchprac.service.ApiService1;
import org.example.springbatchprac.service.ApiService2;
import org.example.springbatchprac.service.ApiService3;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ApiStepConfiguration {

    @Bean
    public Step apiMasterStep(
        JobRepository jobRepository,
        Step apiSlaveStep,
        PartitionHandler productPartitionerHandler,
        ProductPartitioner productPartitioner
    ) {
        return new StepBuilder("apiMasterStep", jobRepository)
            .partitioner(apiSlaveStep.getName(), productPartitioner)
            .partitionHandler(productPartitionerHandler)
            .build();
    }

    @Bean
    public PartitionHandler partitionHandler(
        TaskExecutor taskExecutor,
        Step apiSlaveStep
    ) {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setTaskExecutor(taskExecutor);
        handler.setStep(apiSlaveStep);
        handler.setGridSize(3);
        return handler;
    }

    @Bean
    @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setMaxPoolSize(6);
        taskExecutor.setThreadNamePrefix("api-thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public Step apiSlaveStep(JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        ItemReader<Product> itemReader,
        ItemProcessor<Product, ApiRequestVO> itemProcessor,
        ItemWriter<ApiRequestVO> itemWriter) {
        return new StepBuilder("apiSlaveStep", jobRepository)
            .<Product, ApiRequestVO>chunk(10, transactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    }


    @Bean
    @StepScope
    public JpaPagingItemReader<Product> itemReader(
        @Value("#{stepExecutionContext['type']}") String type,
        EntityManagerFactory entityManagerFactory
    ) {
        return new JpaPagingItemReaderBuilder<Product>()
            .entityManagerFactory(entityManagerFactory)
            .name("itemReader")
            .queryString("SELECT p FROM Product p WHERE p.type = :type ORDER BY p.id DESC")
            .parameterValues(Map.of("type", type))
            .pageSize(10)
            .build();
    }

    @Bean
    public ItemProcessor<Product, ApiRequestVO> itemProcessor() {
        ClassifierCompositeItemProcessor<ProductVO, ApiRequestVO> processor = new ClassifierCompositeItemProcessor<>();

        ProcessorClassifier<ProductVO, ItemProcessor<?, ? extends ApiRequestVO>> classifier = new ProcessorClassifier();

        Map<String, ItemProcessor<ProductVO, ApiRequestVO>> processorMap = new HashMap<>();
        processorMap.put("1", new ApiItemProcessor1());
        processorMap.put("2", new ApiItemProcessor2());
        processorMap.put("3", new ApiItemProcessor3());

        classifier.setProcessorMap(processorMap);

        processor.setClassifier(classifier);

        return product -> processor.process(ProductVO.from(product));
    }

    @Bean
    public ItemWriter<ApiRequestVO> itemWriter() {
        ClassifierCompositeItemWriter<ApiRequestVO> writer = new ClassifierCompositeItemWriter<>();

        WriterClassifier<ApiRequestVO, ItemWriter<? super ApiRequestVO>> classifier = new WriterClassifier();

        Map<String, ItemWriter<ApiRequestVO>> writerMap = new HashMap<>();
        writerMap.put("1", new ApiItemWriter1(new ApiService1()));
        writerMap.put("2", new ApiItemWriter2(new ApiService2()));
        writerMap.put("3", new ApiItemWriter3(new ApiService3()));

        classifier.setWriterMap(writerMap);

        writer.setClassifier(classifier);

        return writer;
    }

}
