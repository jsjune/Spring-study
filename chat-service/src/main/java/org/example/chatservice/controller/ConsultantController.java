package org.example.chatservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chatservice.dtos.ChatroomDto;
import org.example.chatservice.dtos.MemberDto;
import org.example.chatservice.service.ConsultantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consultants")
@Slf4j
@RequiredArgsConstructor
public class ConsultantController {
    private final ConsultantService consultantService;

    @PostMapping
    @ResponseBody
    public MemberDto saveMember(@RequestBody MemberDto memberDto) {
        log.info("memberDto: {}", memberDto);
        return consultantService.saveMember(memberDto);
    }

    @GetMapping
    public String idex() {
        return "consultants/index.html";
    }

    @ResponseBody
    @GetMapping("/chats")
    public Page<ChatroomDto> getAllChatrooms(Pageable pageable) {
        System.out.println("pageable = " + pageable);
        return consultantService.getAllChatrooms(pageable);
    }
}
