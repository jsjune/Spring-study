package com.example.sns.domain.follow.service;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.entity.Follow;
import com.example.sns.domain.follow.repository.FollowRepository;
import com.example.sns.domain.member.dto.MemberDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowReadService {

    private final FollowRepository followRepository;

    public List<FollowDto> getFollowings(Long memberId) {
        List<Follow> findFollows = followRepository.findAllByFromMemberId(memberId);
        return findFollows.stream().map(o -> new ModelMapper().map(o, FollowDto.class)).toList();
    }
}
