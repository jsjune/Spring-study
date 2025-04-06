package org.example.springmysqlelasticsearch.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldSort.Builder;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.springmysqlelasticsearch.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElasticsearchProductRepository {
    private final ElasticsearchClient elasticsearchClient;

    public SearchResponse<ProductDto> searchProducts(String query) throws IOException {
        Query q = QueryBuilders.match()
            .field("name")
            .query(query)
            .build()._toQuery();
        SearchRequest request = new SearchRequest.Builder()
            .query(q)
            .index("products")
            .size(5)
            .sort(List.of(
                new SortOptions.Builder()
                    .field(new Builder()
                        .field("created_at")
                        .order(SortOrder.Desc)
                        .build())
                    .build())
            ).build();
        return elasticsearchClient.search(request, ProductDto.class);
    }
}
