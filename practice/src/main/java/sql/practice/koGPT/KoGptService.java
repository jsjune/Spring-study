package sql.practice.koGPT;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sql.practice.koGPT.common.ApiRestTemplateComponent;
import sql.practice.koGPT.common.ObjectMapperUtil;

@Service
@RequiredArgsConstructor
public class KoGptService {
    @Value("${kogpt-api.host}")
    private String host;

    @Value("${kogpt-api.endpoint}")
    private String endPoint;

    private final ApiRestTemplateComponent apiRestTemplateComponent;

    public Header requestKoGpt(KoGptApiRequestDto koGptApiRequestDto, String restApiKey) {
        String url = host+endPoint;

        HttpHeaders headers = getHttpHeaders(restApiKey);
        String jsonParam = ObjectMapperUtil.writeValueAsString(koGptApiRequestDto);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonParam, headers);

        try{
            Future<ResponseEntity<String>> responseEntityFuture = apiRestTemplateComponent.postForEntity(
                url, httpEntity, String.class, koGptApiRequestDto);
            ResponseEntity<String> responseEntity = responseEntityFuture.get();
            KoGptApiResponseDto baseDto = ObjectMapperUtil.koGptReadValue(
                responseEntity.getBody(), KoGptApiResponseDto.class);
            return Header.OK(baseDto);

        } catch (Exception e) {
            return Header.ERROR(e.getMessage());
        }

    }

    private HttpHeaders getHttpHeaders(String restApiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            headers.set("Authorization", "KakaoAK " + restApiKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return headers;
    }
}
