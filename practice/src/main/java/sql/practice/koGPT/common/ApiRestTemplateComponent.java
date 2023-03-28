package sql.practice.koGPT.common;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class ApiRestTemplateComponent {

    final RestTemplate restTemplate;

    @Async
    public <T> Future<ResponseEntity<T>> getForEntity(String url, HttpEntity<T> httpEntity, Class<T> t) {
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, t);
        return CompletableFuture.completedFuture(responseEntity);
    }

    @Async
    public <T> Future<ResponseEntity<T>> postForEntity(String url, HttpEntity<T> httpEntity, Class<T> t, Object param) throws InterruptedException, ExecutionException {
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, t, param);
        return CompletableFuture.completedFuture(responseEntity);
    }

}
