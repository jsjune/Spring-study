package com.example.sns.domain.follow.service;

import com.example.sns.domain.follow.entity.Follow;
import com.example.sns.domain.follow.repository.FollowRepository;
import com.example.sns.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowWriteService {

    private final FollowRepository followRepository;

    public void register(MemberDto fromMember, MemberDto toMember) {
        Assert.isTrue(!fromMember.getId().equals(toMember.getId()), "From, To 회원이 동일합니다.");

        Follow follow = Follow.builder()
            .fromMemberId(fromMember.getId())
            .toMemberId(toMember.getId())
            .build();
        followRepository.save(follow);
    }
}
