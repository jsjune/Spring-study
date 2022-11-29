package com.example.sns.domain.member.service;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.dto.MemberNicknameHistoryDto;
import com.example.sns.domain.member.entity.Member;
import com.example.sns.domain.member.entity.MemberNicknameHistory;
import com.example.sns.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.sns.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        return new ModelMapper().map(member, MemberDto.class);
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        List<MemberNicknameHistory> findMember = memberNicknameHistoryRepository.findAllByMemberId(
            memberId);
        return findMember.stream()
            .map(o -> new ModelMapper().map(o, MemberNicknameHistoryDto.class)).toList();
    }

    public List<MemberDto> getMembers(List<Long> ids) {
        List<Member> findMember = memberRepository.findAllByIdIn(ids);
        return findMember.stream()
            .map(o -> new ModelMapper().map(o, MemberDto.class)).toList();
    }
}
