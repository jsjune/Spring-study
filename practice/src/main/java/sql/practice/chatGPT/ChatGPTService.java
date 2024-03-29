package sql.practice.chatGPT;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGPTService {

    @Value("${chatGPT-api.key}")
    public String apiKey;
    @Value("${chatGPT-api.host}")
    public String host;
    @Value("${chatGPT-api.endpoint}")
    public String endpoint;

    public String requestJa(ChatGPTRequestDto requestDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", "Bearer "+ apiKey);

        String prompt = papagoAPItest(requestDto.getPrompt(), "ko", "ja");
        prompt = papagoAPItest(requestDto.getPrompt(), "ja", "en");

        // Body 생성
        String bodyJson = "{\r\n" +
            "  \"model\": \"text-davinci-003\",\r\n" +
            "    \"prompt\": \"" + prompt + "\",\r\n" +
            "    \"max_tokens\": 2000,\r\n" +
            "    \"user\": \"1\"\r\n"+
            "}";

        // HTTP 요청 보내기
        HttpEntity<String> kakaoTokenRequest =new HttpEntity<>(bodyJson,headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
            "https://api.openai.com/v1/completions",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String answer = jsonNode.get("choices").get(0).get("text").asText();

        answer = papagoAPItest(answer, "en", "ja");
        answer = papagoAPItest(answer, "ja", "ko");
        return answer;
    }

    public String requestEn(ChatGPTRequestDto requestDto) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", "Bearer "+ apiKey);

        String prompt = papagoAPItest(requestDto.getPrompt(), "ko", "en");

        // Body 생성
        StringBuilder sb = new StringBuilder();
        sb.append("{\r\n");
        sb.append("\"model\"").append(":").append("\"text-davinci-003\",").append("\r\n");
        sb.append("\"prompt\"").append(":").append("\"" + requestDto.getPrompt() + "\",").append("\r\n");
        sb.append("\"max_tokens\"").append(":").append(2000 + ",").append("\r\n");
        sb.append("\"user\"").append(":").append("\"1\"").append("\r\n");
        sb.append("}");
        String bodyJson = sb.toString();
//        String bodyJson = "{\r\n" +
//            "  \"model\": \"text-davinci-003\",\r\n" +
//            "    \"prompt\": \"" + requestDto.getPrompt() + "\",\r\n" +
//            "    \"max_tokens\": 2000,\r\n" +
//            "    \"user\": \"1\"\r\n"+
//            "}";

//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("model","text-davinci-003");
//        body.add("prompt",requestDto.getPrompt());
//        body.add("max_tokens","2000");
//        body.add("user","1");

        // HTTP 요청 보내기
        HttpEntity<String> kakaoTokenRequest =new HttpEntity<>(bodyJson,headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
            "https://api.openai.com/v1/completions",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String answer = jsonNode.get("choices").get(0).get("text").asText();

        answer = papagoAPItest(answer, "en", "ko");
        return answer;
    }

    public String papagoAPItest(String question, String source, String target) throws Exception {
        /**
         * 언어 : en , ko
         */

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.add("X-Naver-Client-Id", "aaa");
        headers.add("X-Naver-Client-Secret", "bbb");

        // Body 생성
//        String question = "do you know WW2? tell me about it";
        question = question.replace("\n", "");
//        question = question.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", " ");
//        System.out.println(source + "->" + target + " : papago source = " + question);
        String body = "source="+ source +"&target=" + target + "&text="+ question;

        // HTTP 요청 보내기
        HttpEntity<String> request =new HttpEntity<>(body,headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://openapi.naver.com/v1/papago/n2mt",
                HttpMethod.POST,
                request,
                String.class
        );

//        System.out.println("bodyJson = " + body);

        // HTTP 응답 (JSON)
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String answer  = jsonNode.get("message").get("result").get("translatedText").asText();

//        System.out.println("jsonNode = " + jsonNode);
//        System.out.println("answer = " + answer);

        return answer;
    }

    public static class ResponseChatGpt{
        List<String> text = new ArrayList<>();
    }

}
