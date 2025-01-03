package org.example.springbatchprac.batch.chunk.wrtier;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.example.springbatchprac.batch.domain.ApiRequestVO;
import org.example.springbatchprac.batch.domain.ApiResponseVO;
import org.example.springbatchprac.service.AbstractApiService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Slf4j
public class ApiItemWriter3 extends FlatFileItemWriter<ApiRequestVO> {

    private final AbstractApiService apiService;

    public ApiItemWriter3(AbstractApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void write(Chunk<? extends ApiRequestVO> items) throws Exception {
        System.out.println("----------------------------------");
        items.forEach(item -> System.out.println("items = " + item));
        System.out.println("----------------------------------");

        ApiResponseVO response = apiService.service(items.getItems());
        System.out.println("response = " + response);

        items = new Chunk<>(items.getItems().stream().map(item -> item.withApiResponseVO(response)).toList());

        super.setResource(new FileSystemResource("E:\\Backend\\spring\\spring-batch-prac\\src\\main\\resources\\product3.txt"));
        super.open(new ExecutionContext());
        super.setLineAggregator(new DelimitedLineAggregator<>());
        super.setAppendAllowed(true);
        super.write(items);
    }
}
