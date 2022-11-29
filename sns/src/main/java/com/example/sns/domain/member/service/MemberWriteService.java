package com.example.sns.domain.member.service;

import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.dto.RegisterMemberCommand;
import com.example.sns.domain.member.entity.Member;
import com.example.sns.domain.member.entity.MemberNicknameHistory;
import com.example.sns.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.sns.domain.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberWriteService {

    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    @Transactional
    public MemberDto create(RegisterMemberCommand command) {
        Member member = Member.builder()
            .email(command.email())
            .nickname(command.nickname())
            .birthday(command.birthday())
            .build();

        Member saveMember = memberRepository.save(member);
        saveMemberNicknameHistory(saveMember);

        return new ModelMapper().map(saveMember, MemberDto.class);
    }

    @Transactional
    public void changeNickname(Long memberId, String nickname) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);

        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member saveMember) {
        MemberNicknameHistory history = MemberNicknameHistory.builder()
            .memberId(saveMember.getId())
            .nickname(saveMember.getNickname())
            .build();
        memberNicknameHistoryRepository.save(history);
    }
}
