package org.example.springbatchprac.batch.job.file;

import jakarta.persistence.EntityManagerFactory;
import org.example.springbatchprac.batch.domain.Product;
import org.example.springbatchprac.batch.domain.ProductVO;
import org.example.springbatchprac.util.ReflectionUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FileJobConfiguration {

    @Bean
    public Job fileJob(
        JobRepository jobRepository,
        JobExecutionListener listener,
        Step fileStep
    ) {
        return new JobBuilder("fileJob", jobRepository)
            .start(fileStep)
            .listener(listener)
            .build();

    }

    @Bean
    public Step fileStep(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        ItemReader<ProductVO> fileItemReader,
        ItemProcessor<ProductVO, Product> fileItemProcessor,
        JpaItemWriter<Product> fileItemWriter
    ) {
        return new StepBuilder("fileStep", jobRepository)
            .<ProductVO, Product>chunk(1000, transactionManager)
            .reader(fileItemReader)
            .processor(fileItemProcessor)
            .writer(fileItemWriter)
            .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<ProductVO> fileItemReader(
        @Value("#{jobParameters['requestDate']}") String requestDate
        ) {
        return new FlatFileItemReaderBuilder<ProductVO>()
            .name("fileItemReader")
            .resource(new ClassPathResource("product_" + requestDate + ".csv"))
            .delimited()
            .names(ReflectionUtils.getFieldNames(ProductVO.class).toArray(String[]::new))
            .targetType(ProductVO.class)
            .linesToSkip(1)
            .build();
    }

    @Bean
    public ItemProcessor<ProductVO, Product> fileItemProcessor() {
        return Product::from;
    }

    @Bean
    public JpaItemWriter<Product> fileItemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Product>()
            .entityManagerFactory(entityManagerFactory)
            .usePersist(true)
            .build();
    }


}
