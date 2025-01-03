package org.example.springbatchprac.service;

import java.io.IOException;
import java.util.List;
import org.example.springbatchprac.batch.domain.ApiInfo;
import org.example.springbatchprac.batch.domain.ApiRequestVO;
import org.example.springbatchprac.batch.domain.ApiResponseVO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Service
public abstract class AbstractApiService {

    public ApiResponseVO service(List<? extends ApiRequestVO> apiRequest) {

        // 중계사업자와 API 연동 작업
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        }).build();

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiInfo apiInfo = new ApiInfo(null, apiRequest);
        HttpEntity<ApiInfo> reqEntity = new HttpEntity<>(apiInfo, headers);

        return doApiService(restTemplate, apiInfo);

    }

    protected abstract ApiResponseVO doApiService(RestTemplate restTemplate, ApiInfo apiInfo);
}
