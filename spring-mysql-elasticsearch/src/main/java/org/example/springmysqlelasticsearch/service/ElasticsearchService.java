package org.example.springmysqlelasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.springmysqlelasticsearch.dto.ProductDto;
import org.example.springmysqlelasticsearch.entity.Product;
import org.example.springmysqlelasticsearch.repository.ElasticsearchProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticsearchService {

    private final ElasticsearchProductRepository elasticsearchProductRepository;

    public List<ProductDto> searchProducts(String query) throws IOException {
        SearchResponse<ProductDto> response = elasticsearchProductRepository.searchProducts(query);
        return response.hits().hits().stream()
            .map(Hit::source)
            .collect(Collectors.toList());
    }
}
