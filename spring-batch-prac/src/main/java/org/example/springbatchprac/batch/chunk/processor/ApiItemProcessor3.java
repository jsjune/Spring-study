package org.example.springbatchprac.batch.chunk.processor;

import org.example.springbatchprac.batch.domain.ApiRequestVO;
import org.example.springbatchprac.batch.domain.Product;
import org.example.springbatchprac.batch.domain.ProductVO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ApiItemProcessor3 implements ItemProcessor<ProductVO, ApiRequestVO> {

    @Override
    public ApiRequestVO process(ProductVO product) throws Exception {
        return ApiRequestVO.from(product);
    }
}
