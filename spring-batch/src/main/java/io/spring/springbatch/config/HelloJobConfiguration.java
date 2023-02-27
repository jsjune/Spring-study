package io.spring.springbatch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public static final String JOB_NAME = "job";
    public static final String Step_1_NAME = "step1";
    public static final String Step_2_NAME = "step2";

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(helloStep1())
//                .next(helloStep2())
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get(Step_1_NAME)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===================================");
                    System.out.println(" >> Hello Spring Batch");
                    System.out.println("===================================");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

//    @Bean
//    public Step helloStep2() {
//        return stepBuilderFactory.get(Step_2_NAME)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("===================================");
//                    System.out.println(" >> step2 was executed");
//                    System.out.println("===================================");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
}
