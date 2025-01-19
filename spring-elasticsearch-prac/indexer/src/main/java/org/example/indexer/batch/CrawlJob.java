package org.example.indexer.batch;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.indexer.dto.ProductDto;
import org.example.indexer.entity.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CrawlJob {

    private static final int CHUNK_SIZE = 1000;

    @Bean
    public Job crawl_job(
        JobRepository jobRepository,
        Step crawlJob_step1
    ) {
        return new JobBuilder("crawl_job", jobRepository)
            .start(crawlJob_step1)
            .build();
    }

    @Bean
    public Step crawlJob_step1(
        JobRepository jobRepository,
        PlatformTransactionManager transactionManager,
        FlatFileItemReader<ProductDto> crawlJob_csvItemReader,
        ItemProcessor<ProductDto, Product> crawlJob_csvItemProcessor,
        JpaItemWriter<Product> crawlJob_itemWriter
    ) {
        return new StepBuilder("crawlJob_step1", jobRepository)
            .<ProductDto, Product>chunk(CHUNK_SIZE, transactionManager)
            .reader(crawlJob_csvItemReader)
            .processor(crawlJob_csvItemProcessor)
            .writer(crawlJob_itemWriter)
            .build();
    }

    @Bean
    public FlatFileItemReader<ProductDto> crawlJob_csvItemReader() {
        return new FlatFileItemReaderBuilder<ProductDto>()
            .name("crawlJob_csvItemReader")
            .resource(new ClassPathResource("product_sample.csv"))
            .linesToSkip(1)
            .delimited()
            .names("asin", "title", "img_url", "product_url", "stars", "reviews", "price", "list_price", "category_id", "is_best_seller", "bought_in_last_month")
            .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(ProductDto.class);
            }})
            .build();
    }

    @Bean
    public ItemProcessor<ProductDto, Product> crawlJob_csvItemProcessor() {
        return item -> {
            Product product = new Product();
            if (item.getStars() >= 4.5 &&
                item.isBestSeller() && item.getBoughtInLastMonth() >= 200) {
                product.setRecommendSeller(true);
            }
            product.setId(null);
            product.setAsin(item.getAsin());
            product.setTitle(item.getTitle());
            product.setImgUrl(item.getImgUrl());
            product.setProductUrl(item.getProductUrl());
            product.setStars(item.getStars());
            product.setReviews(item.getReviews());
            product.setPrice(item.getPrice());
            product.setListPrice(item.getListPrice());
            product.setCategoryId(item.getCategoryId());
            product.setBestSeller(item.isBestSeller());
            product.setBoughtInLastMonth(item.getBoughtInLastMonth());
            return product;
        };
    }

    @Bean
    JpaItemWriter<Product> crawlJob_itemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Product>()
            .entityManagerFactory(entityManagerFactory)
            .usePersist(true)
            .build();
    }
}
