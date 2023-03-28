package sql.practice.koGPT;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kgpt")
public class KoGptApiController {

    private final KoGptService koGptService;

    @PostMapping("/request")
    public ResponseEntity makeSentence(@RequestParam String restApiKey,
        @RequestBody KoGptApiRequestDto requestDto) {
        KoGptApiRequestDto koGptApiRequestDto = KoGptApiRequestDto.builder()
            .prompt(requestDto.getPrompt())
            .max_tokens(requestDto.getMax_tokens())
            .temperature(requestDto.getTemperature())
            .top_p(requestDto.getTop_p())
            .n(requestDto.getN())
            .build();
        return ResponseEntity.ok().body(koGptService.requestKoGpt(koGptApiRequestDto, restApiKey));
    }
}
