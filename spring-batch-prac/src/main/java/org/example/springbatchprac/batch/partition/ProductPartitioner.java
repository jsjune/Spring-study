package org.example.springbatchprac.batch.partition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.springbatchprac.batch.domain.ProductVO;
import org.example.springbatchprac.service.ProductService;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPartitioner implements Partitioner {
    private final ProductService productService;

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        List<String> productTypes = productService.getDistinctProductTypes();
        Map<String, ExecutionContext> result = new HashMap<>();
        int number = 0;

        for (String productType : productTypes) {
            ExecutionContext context = new ExecutionContext();
            result.put("partition" + number, context);
            context.put("type", productType);
            number++;
        }

        return result;
    }
}
