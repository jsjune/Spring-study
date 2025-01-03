package org.example.springbatchprac.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springbatchprac.batch.domain.ProductRepository;
import org.example.springbatchprac.batch.domain.ProductVO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<String> getDistinctProductTypes() {
        return productRepository.findDistinctTypes();
    }
}
