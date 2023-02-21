package sql.practice.chatGPT;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import java.time.Instant;

public class ChatGptTest {

    private static final String apiKey = "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";

    public static void main(String[] args) {
        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest completionRequest = CompletionRequest.builder()
            .prompt("노트북, 마감임박, 50%할인 단어로 광고문구를 만들어줘")
            .maxTokens(1024)
            .temperature(1.0)
            .topP(1.0)
            .model("text-davinci-003")
            .build();
        Instant before = Instant.now();
        try {
            service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
        } catch (RuntimeException e) {
            ChatGptTest.main(args);
        }
        Duration time = Duration.between(before, Instant.now());
        System.out.println("Time : " + time.getNano() / 1000000 + "ms");

    }

}
