package org.example.springmysqlelasticsearch.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springmysqlelasticsearch.dto.ProductDto;
import org.example.springmysqlelasticsearch.service.ElasticsearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class ElasticsearchController {

    private final ElasticsearchService elasticsearchService;

    @GetMapping
    public List<ProductDto> search(@RequestParam String query) throws IOException {
        return elasticsearchService.searchProducts(query);
    }
}
