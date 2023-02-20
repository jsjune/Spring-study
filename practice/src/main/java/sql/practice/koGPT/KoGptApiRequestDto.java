package sql.practice.koGPT;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class KoGptApiRequestDto {

    private String prompt;
    private Integer max_tokens;
    private Double temperature;
    private Double top_p;
    private Integer n;

    public KoGptApiRequestDto(String prompt, Integer max_tokens, Double temperature, Double top_p,
        Integer n) {
        this.prompt = prompt;
        this.max_tokens = max_tokens;
        this.temperature = (temperature == 0 ? 1.0 : temperature);
        this.top_p = (top_p == 0 ? 1.0 : top_p);
        this.n = (n == 0 ? 1 : n);
    }
}
