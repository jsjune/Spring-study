package org.example.springbatchprac.service;

import org.example.springbatchprac.batch.domain.ApiInfo;
import org.example.springbatchprac.batch.domain.ApiResponseVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService3 extends AbstractApiService{

    @Override
    public ApiResponseVO doApiService(RestTemplate restTemplate, ApiInfo apiInfo){

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/api/product/3", apiInfo, String.class);

        int statusCodeValue = response.getStatusCodeValue();
        ApiResponseVO apiResponseVO = new ApiResponseVO(statusCodeValue , response.getBody());

        return apiResponseVO;
    }
}
