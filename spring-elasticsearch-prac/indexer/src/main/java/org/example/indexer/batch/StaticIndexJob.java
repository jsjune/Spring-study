package org.example.indexer.batch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetAliasRequest;
import co.elastic.clients.elasticsearch.indices.GetAliasResponse;
import co.elastic.clients.elasticsearch.indices.UpdateAliasesRequest;
import co.elastic.clients.elasticsearch.indices.UpdateAliasesResponse;
import jakarta.persistence.EntityManagerFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.indexer.dto.ESProductDto;
import org.example.indexer.entity.Product;
import org.example.indexer.helper.ESHelper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StaticIndexJob {

    private final ElasticsearchClient elasticsearchClient;
    private static final int CHUNK_SIZE = 1000;
    private static final int PAGE_SIZE = 1000;
    private static final int POOL_SIZE = 4;
    private Date lastUpdatedAt;

    @Bean
    public Job static_index_job(
        JobRepository jobRepository,
        Step staticIndexJob_step1,
        Step staticIndexJob_step2,
        Step staticIndexJob_step3
    ) {
        return new JobBuilder("static_index_job", jobRepository)
            .start(staticIndexJob_step1)
            .next(staticIndexJob_step2)
            .next(staticIndexJob_step3)
            .build();
    }

    @Bean
    public Step staticIndexJob_step1(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        JpaPagingItemReader<Product> fullItemReader,
        ItemWriter<Product> staticIndexJob_elasticBulkWriter,
        TaskExecutor executor
    ) {
        log.info("StaticIndexJob Step1 start");
        lastUpdatedAt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("lastUpdatedAt: {}", formatter.format(lastUpdatedAt));
        return new StepBuilder("staticIndexJob_step1", jobRepository)
            .<Product, Product>chunk(CHUNK_SIZE, transactionManager)
            .reader(fullItemReader)
            .writer(staticIndexJob_elasticBulkWriter)
            .listener(new StepExecutionListener() {
                @Override
                public void beforeStep(StepExecution stepExecution) {
                    lastUpdatedAt = new Date();
                    stepExecution.getExecutionContext().put("lastUpdatedAt", lastUpdatedAt);
                }
            })
            .taskExecutor(executor)
            .build();
    }

    @Bean
    public JpaPagingItemReader<Product> fullItemReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<Product>()
            .name("fullItemReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT p FROM Product p")
            .pageSize(PAGE_SIZE)
            .build();
    }

    @Bean
    public Step staticIndexJob_step2(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        JpaPagingItemReader<Product> staticIndexJob_incrementProductsReader,
        ItemWriter<Product> staticIndexJob_elasticBulkWriter,
        TaskExecutor executor
    ) {
        log.info("staticIndexJob_step2 start");
        return new StepBuilder("staticIndexJob_step2", jobRepository)
            .<Product, Product>chunk(CHUNK_SIZE, transactionManager)
            .reader(staticIndexJob_incrementProductsReader)
            .writer(staticIndexJob_elasticBulkWriter)
            .taskExecutor(executor)
            .build();
    }

    @Bean
    public JpaPagingItemReader<Product> staticIndexJob_incrementProductsReader(
        EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<Product>()
            .name("staticIndexJob_incrementProductsReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT p FROM Product p WHERE p.updatedAt > :lastUpdatedAt")
            .parameterValues(Map.of("lastUpdatedAt", lastUpdatedAt))
            .pageSize(PAGE_SIZE)
            .build();
    }

    @Bean
    @JobScope
    public Step staticIndexJob_step3(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        @Value("#{jobParameters[date]}") String date
    ) {
        log.debug("staticIndexJob_step3 start");
        return new StepBuilder("staticIndexJob_step3", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                changeElasticsearchAlias(date);
                return RepeatStatus.FINISHED;
            }, transactionManager)
            .build();
    }

    @Bean
    @StepScope
    @JobScope
    public ItemWriter<Product> staticIndexJob_elasticBulkWriter(
        @Value("#{jobParameters[date]}") String date,
        ElasticsearchClient elasticsearchClient
    ) {
        return products -> {
            log.info("Writing products: {}", products);
            BulkRequest.Builder br = new BulkRequest.Builder();


            for (Product product : products) {
                ESProductDto esProductDto = new ESProductDto(product.getId(), product.getAsin(), product.getTitle(), product.getImgUrl(), product.getProductUrl(), product.getStars(), product.getReviews(), product.getPrice(), product.getListPrice(), product.getCategoryId(), product.isBestSeller(), product.getBoughtInLastMonth(), product.isRecommendSeller(), product.getCreatedAt(), product.getUpdatedAt());
                br.operations(op -> op
                    .index(idx -> idx
                        .index("products_" + date)
                        .id("product_" + product.getId())
                        .document(esProductDto)
                    )
                );
            }

            ESHelper.DoBulk(elasticsearchClient, br);
        };
    }

    private void changeElasticsearchAlias(String date) throws IOException {
        String newIndexName = "products_" + date;
        String oldIndex = null;

        try {
            GetAliasResponse response = elasticsearchClient.indices().getAlias(
                GetAliasRequest.of(b -> b.name("products")));
            oldIndex = response.result().keySet().iterator().next();
        } catch (Exception e) {
            log.warn("Alias 'products'가 존재하지 않습니다. 새로 추가합니다.");
        }

        UpdateAliasesRequest.Builder builder = new UpdateAliasesRequest.Builder();

        if (oldIndex != null) {
            String finalOldIndex = oldIndex;
            builder.actions(actions -> actions
                .remove(remove -> remove.index(finalOldIndex).alias("products")));
        }

        builder.actions(actions -> actions
            .add(add -> add.index(newIndexName).alias("products")));

        UpdateAliasesResponse updateAliasesResponse = elasticsearchClient.indices()
            .updateAliases(builder.build());

        if (updateAliasesResponse.acknowledged()) {
            log.info("'products' alias가 '{}'로 교체되었습니다.", newIndexName);
            if (oldIndex != null) removeOldIndex(oldIndex);
        } else {
            log.error("Alias 교체 실패.");
        }
    }

    private void removeOldIndex(String oldIndex) throws IOException {
        DeleteIndexRequest request = DeleteIndexRequest.of(b -> b.index(oldIndex));
        DeleteIndexResponse response = elasticsearchClient.indices().delete(request);
        if (response.acknowledged()) {
            log.info("Old index '{}'가 성공적으로 삭제되었습니다.", oldIndex);
        } else {
            log.error("Old index '{}' 삭제 실패.", oldIndex);
        }
    }

    @Bean
    public TaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(POOL_SIZE);
        executor.setMaxPoolSize(POOL_SIZE * 2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("multi-thread-");
        executor.setWaitForTasksToCompleteOnShutdown(Boolean.TRUE);
        executor.initialize();
        return executor;
    }
}
