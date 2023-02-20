package sql.practice.koGPT;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class KoGptApiResponseDto {
    private String id;
    private List<Generation> generations;
    private Usage usage;

    @Data
    static class Generation{
        private String text;
        private Integer tokens;
    }

    @Data
    static class Usage{
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;
        @JsonProperty("generated_tokens")
        private Integer generatedTokens;
        @JsonProperty("total_tokens")
        private Integer totalTokens;
    }
}
