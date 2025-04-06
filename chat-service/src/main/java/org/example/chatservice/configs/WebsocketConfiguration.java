//package org.example.chatservice.configs;
//
//import lombok.RequiredArgsConstructor;
//import org.example.chatservice.handlers.WebSocketChatHandler;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@EnableWebSocket
//@Configuration
//@RequiredArgsConstructor
//public class WebsocketConfiguration implements WebSocketConfigurer {
//
//    private final WebSocketChatHandler webSocketChatHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(webSocketChatHandler, "/ws/chats");
//    }
//}
