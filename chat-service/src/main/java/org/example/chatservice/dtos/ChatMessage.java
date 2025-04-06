package org.example.chatservice.dtos;

import org.example.chatservice.entities.Message;

public record ChatMessage(
    Long chatroomId,
    String sender,
    String message
) {

    public static ChatMessage from(Message message) {
        return new ChatMessage(
            message.getChatroom().getId(),
            message.getMember().getNickname(),
            message.getText()
        );
    }

}
