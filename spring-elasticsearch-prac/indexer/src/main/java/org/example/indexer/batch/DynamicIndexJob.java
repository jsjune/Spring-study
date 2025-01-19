package org.example.indexer.batch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.indices.GetAliasRequest;
import co.elastic.clients.elasticsearch.indices.GetAliasResponse;
import jakarta.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.indexer.dto.ESProductDto;
import org.example.indexer.entity.Product;
import org.example.indexer.helper.ESHelper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DynamicIndexJob {

    private final ElasticsearchClient elasticsearchClient;

    private static final int CHUNK_SIZE = 1000;
    private static final int PAGE_SIZE = 1000;

    @Bean
    public Job dynamicJob(
        JobRepository jobRepository,
        Step dynamicIndexJob_step1
    ) {
        return new JobBuilder("dynamicJob", jobRepository)
            .start(dynamicIndexJob_step1)
            .incrementer(new RunIdIncrementer())
            .build();
    }

    @Bean
    public Step dynamicIndexJob_step1(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        JpaPagingItemReader<Product> incrementProductReader,
        ItemWriter<Product> dynamicIndexJob_elasticBulkWriter
    ) {
        return new StepBuilder("dynamicIndexJob_step1", jobRepository)
            .<Product, Product>chunk(CHUNK_SIZE, transactionManager)
            .reader(incrementProductReader)
            .writer(dynamicIndexJob_elasticBulkWriter)
            .build();
    }

    @Bean
    public JpaPagingItemReader<Product> incrementProductReader(
        EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReader<>() {{
            setEntityManagerFactory(entityManagerFactory);
            setQueryString("SELECT p FROM Product p WHERE p.updatedAt > :lastUpdatedAt");
            // 1 minute ago
            Date lastUpdatedAt = new Date(System.currentTimeMillis() - (60 * 1000));
            setParameterValues(Collections.singletonMap("lastUpdatedAt", lastUpdatedAt));
            setPageSize(PAGE_SIZE);
        }};
    }

    @Bean
    @StepScope
    public ItemWriter<Product> dynamicIndexJob_elasticBulkWriter() {
        return products -> {
            GetAliasRequest request = GetAliasRequest.of(b -> b.name("products"));

            // Alias 존재 여부 확인 요청 실행
            GetAliasResponse response = elasticsearchClient.indices().getAlias(request);

            String currentIndex = response.result().keySet().iterator().next();

            log.info("Writing products: {}", products);
            BulkRequest.Builder br = new BulkRequest.Builder();

            for (Product product : products) {
                ESProductDto esProductDto = new ESProductDto(product.getId(), product.getAsin(), product.getTitle(), product.getImgUrl(), product.getProductUrl(), product.getStars(), product.getReviews(), product.getPrice(), product.getListPrice(), product.getCategoryId(), product.isBestSeller(), product.getBoughtInLastMonth(), product.isRecommendSeller(), product.getCreatedAt(), product.getUpdatedAt());
                br.operations(op -> op
                    .index(idx -> idx
                        .index(currentIndex)
                        .id("product_" + product.getId())
                        .document(esProductDto)
                    )
                );
            }

            ESHelper.DoBulk(elasticsearchClient, br);
        };
    }

}
