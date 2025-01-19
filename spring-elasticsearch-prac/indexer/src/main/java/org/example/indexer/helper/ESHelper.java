package org.example.indexer.helper;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ESHelper {
    public static void DoBulk(ElasticsearchClient elasticsearchClient, BulkRequest.Builder br)
        throws IOException {
        log.info("Executing bulk");
        long startTime = System.currentTimeMillis();

        BulkResponse result = elasticsearchClient.bulk(br.build());

        long endTime = System.currentTimeMillis();
        log.info("Bulk execution completed in {} ms", (endTime - startTime));

        if (result.errors()) {
            log.error("Bulk had errors");
            for (BulkResponseItem item : result.items()) {
                if (item.error() != null) {
                    log.error(item.error().reason());
                }
            }
        }
    }
}
