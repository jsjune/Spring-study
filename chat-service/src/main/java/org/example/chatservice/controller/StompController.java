package org.example.chatservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dtos.ChatMessage;
import org.example.chatservice.dtos.ChatroomDto;
import org.example.chatservice.service.ChatService;
import org.example.chatservice.vos.CustomOAuth2User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompController {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic channelTopic;
    private final ChatService chatService;
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chats/{chatroomId}") // /pub/chats
//    @SendTo("/sub/chats/{chatroomId}")
    public void handleMessage(@AuthenticationPrincipal Principal principal,@DestinationVariable Long chatroomId, @Payload Map<String, String> payload) {
        System.out.println("payload = " + payload);
        log.info("{} sent {} in {}",principal.getName(), payload, chatroomId);
        CustomOAuth2User user = (CustomOAuth2User)((AbstractAuthenticationToken)principal).getPrincipal();
        chatService.saveMessage(user.getMember(), chatroomId, payload.get("message"));
        redisTemplate.convertAndSend(channelTopic.getTopic(), new ChatMessage(chatroomId, user.getName(), payload.get("message")));
//        return new ChatMessage(user.getName(), payload.get("message"));
    }

    public void sendMessage(String payload) {
        try {
            ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
            ChatroomDto chatroomDto = chatService.getChatroom(chatMessage.chatroomId());
            messagingTemplate.convertAndSend("/sub/chats/updates", chatroomDto);
            messagingTemplate.convertAndSend("/sub/chats/" + chatMessage.chatroomId(), chatMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
