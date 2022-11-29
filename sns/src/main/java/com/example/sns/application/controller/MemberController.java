package com.example.sns.application.controller;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.dto.MemberNicknameHistoryDto;
import com.example.sns.domain.member.dto.RegisterMemberCommand;
import com.example.sns.domain.member.entity.Member;
import com.example.sns.domain.member.repository.MemberRepository;
import com.example.sns.domain.member.service.MemberReadService;
import com.example.sns.domain.member.service.MemberWriteService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberReadService memberReadService;

    @Operation(summary = "회원정보 등록")
    @PostMapping("/")
    public MemberDto create(@RequestBody RegisterMemberCommand command) {
        return memberWriteService.create(command);
    }

    @Operation(summary = "회원정보 단건 조회")
    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable long id) {
        return memberReadService.getMember(id);
    }

    @Operation(summary = "회원이름 변경")
    @PostMapping("/{id}/name")
    public MemberDto changeMember(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @Operation(summary = "회원이름 변경내역 조회")
    @GetMapping("/{id}/name-histories")
    public List<MemberNicknameHistoryDto> getMemberNicknameHistories(@PathVariable("id") Long memberId) {
        return memberReadService.getNicknameHistories(memberId);
    }
}
