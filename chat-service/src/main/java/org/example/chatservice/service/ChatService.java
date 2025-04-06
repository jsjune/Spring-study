package org.example.chatservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dtos.ChatroomDto;
import org.example.chatservice.entities.Chatroom;
import org.example.chatservice.entities.Member;
import org.example.chatservice.entities.MemberChatroomMapping;
import org.example.chatservice.entities.Message;
import org.example.chatservice.repository.ChatroomRepository;
import org.example.chatservice.repository.MemberChatroomMappingRepository;
import org.example.chatservice.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final MessageRepository messageRepository;
    private final ChatroomRepository chatroomRepository;
    private final MemberChatroomMappingRepository memberChatroomMappingRepository;

    public Chatroom createChatroom(Member member, String title) {
        Chatroom chatroom = Chatroom.builder()
            .title(title)
            .createdAt(LocalDateTime.now())
            .build();
        chatroom = chatroomRepository.save(chatroom);
        MemberChatroomMapping memberChatroomMapping = chatroom.addMember(member);
        memberChatroomMappingRepository.save(memberChatroomMapping);
        return chatroom;
    }

    public Boolean joinChatroom(Member member, Long newChatroomId, Long currentChatroomId) {
        if (currentChatroomId != null) {
            updateLastCheckedAt(member, currentChatroomId);
        }
        if (memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(),
            newChatroomId)) {
            log.info("이미 참여한 채팅방입니다.");
            return false;
        }
        Optional<Chatroom> chatroom = chatroomRepository.findById(newChatroomId);
        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
            .member(member)
            .chatroom(chatroom.get())
            .build();
        memberChatroomMappingRepository.save(memberChatroomMapping);
        return true;
    }

    private void updateLastCheckedAt(Member member, Long currentChatroomId) {
        memberChatroomMappingRepository.findByMemberIdAndChatroomId(member.getId(), currentChatroomId)
            .ifPresent(memberChatroomMapping -> {
                memberChatroomMapping.updateLastCheckedAt();
                memberChatroomMappingRepository.save(memberChatroomMapping);
            });
    }

    @Transactional
    public Boolean leaveChatroom(Member member, Long chatroomId) {
        if (!memberChatroomMappingRepository.existsByMemberIdAndChatroomId(member.getId(),
            chatroomId)) {
            log.info("참여하지 않은 채팅방입니다.");
            return false;
        }
        memberChatroomMappingRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);
        return true;
    }

    public List<Chatroom> getChatroomList(Member member) {
        return memberChatroomMappingRepository.findAllByMemberId(member.getId())
            .stream()
            .map(memberChatroomMapping -> {
                Chatroom chatroom = memberChatroomMapping.getChatroom();
                chatroom.setHasNewMessage(messageRepository.existsByChatroomIdAndCreatedAtAfter(chatroom.getId(),
                    memberChatroomMapping.getLastCheckedAt()));
                return chatroom;
            })
            .toList();
    }

    public Message saveMessage(Member member, Long chatroomId, String text) {
        Chatroom chatroom = chatroomRepository.findById(chatroomId).orElseThrow();
        Message message = Message.builder()
            .text(text)
            .member(member)
            .chatroom(chatroom)
            .createdAt(LocalDateTime.now())
            .build();
        return messageRepository.save(message);
    }

    public List<Message> getMessages(Long chatroomId) {
        return messageRepository.findAllByChatroomId(chatroomId);
    }

    @Transactional(readOnly = true)
    public ChatroomDto getChatroom(Long chatroomId) {
        return chatroomRepository.findById(chatroomId)
            .map(ChatroomDto::from)
            .orElseThrow();
    }
}
