package org.example.searcher.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.FieldValueFactorScoreFunction;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionBoostMode;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScore;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScoreMode;
import co.elastic.clients.elasticsearch._types.query_dsl.FunctionScoreQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.searcher.entity.Product;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ElasticsearchRepository {

    private final ElasticsearchClient elasticsearchClient;

    /*
GET products/_search
{
  "search_after": [ 1.48,"B08J4FKXJ2"],
  "query": {
    "function_score": {
      "query": {
        "match": {
          "title": "pants"
        }
      },
      "score_mode": "sum",
      "boost_mode": "replace",
      "functions": [
        {
          "filter": {
            "match": {
              "title": "pants"
            }
          },
          "weight": 1
        },
        {
          "field_value_factor": {
            "field": "stars"
          },
          "weight": 0.1
        },
        {
          "filter": {
            "match": {
              "is_recommend_seller": true
            }
          },
          "weight": 0.2
        }
      ]
    }
  },
  "sort": [
    {
      "_score": {
        "order": "desc"
      }
    },
    {
      "asin": {
        "order": "desc"
      }
    }
  ]
}
    * */

    public SearchResponse<Product> searchProducts(String query, int size, Double lastScore, String lastId) throws IOException {
        FunctionScoreQuery functionScore = QueryBuilders.functionScore()
            .query(QueryBuilders.match()
                .field("title")
                .query(query).build()._toQuery())
            .scoreMode(FunctionScoreMode.Sum)
            .boostMode(FunctionBoostMode.Replace).functions(
                new FunctionScore.Builder()
                    .filter(QueryBuilders.match()
                        .field("title")
                        .query(query).build()._toQuery())
                    .weight(1.0)
                    .build(),
                new FunctionScore.Builder()
                    .fieldValueFactor(new FieldValueFactorScoreFunction.Builder().field("stars").build())
                    .weight(0.1)
                    .build(),
                new FunctionScore.Builder()
                    .filter(QueryBuilders.match()
                        .field("is_recommend_seller")
                        .query("true").build()._toQuery())
                    .weight(0.2)
                    .build()
            )
            .build();

        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder()
            .query(functionScore._toQuery())
            .index("products")
            .size(size)
            .sort(List.of(
                new SortOptions.Builder().field(new FieldSort.Builder().field("_score").order(
                    SortOrder.Desc).build()).build(),
                new SortOptions.Builder().field(new FieldSort.Builder().field("asin").order(SortOrder.Desc).build()).build()
            ));
        if  (lastId!= null && lastScore != null) {
            searchRequestBuilder.searchAfter(List.of(
                FieldValue.of(lastScore),
                FieldValue.of(lastId)
            ));
        }

        SearchRequest searchRequest = searchRequestBuilder
            .build();


        log.info("searchRequest: {}", searchRequest);

        return elasticsearchClient.search(searchRequest, Product.class);

    }
}
