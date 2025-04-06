package org.example.springmysqlelasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;
    @Value("${elasticsearch.port}")
    private int elasticsearchPort;
    @Value("${elasticsearch.username}")
    private String elasticsearchUsername;
    @Value("${elasticsearch.password}")
    private String elasticsearchPassword;

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // 자격 증명 제공자 생성
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
            new AuthScope(elasticsearchHost, elasticsearchPort),
            new UsernamePasswordCredentials(elasticsearchUsername, elasticsearchPassword) // 사용자 이름과 비밀번호
        );

        // RestClient를 사용하여 Elasticsearch 클라이언트 생성
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9200));
        restClientBuilder.setHttpClientConfigCallback(httpClientBuilder ->
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
        );

        RestClient restClient = restClientBuilder.build();

        // RestClientTransport 생성
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // ElasticsearchClient 생성
        return new ElasticsearchClient(transport);
    }
}
