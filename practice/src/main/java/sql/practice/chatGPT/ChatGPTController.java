package sql.practice.chatGPT;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/chatgpt/request/ja")
    public String requestJa(@RequestBody ChatGPTRequestDto requestDto)
        throws Exception {
        return chatGPTService.requestJa(requestDto);
    }

    @PostMapping("/chatgpt/request/en")
    public String requestEn(@RequestBody ChatGPTRequestDto requestDto) throws Exception {
        return chatGPTService.requestEn(requestDto);
    }
}
