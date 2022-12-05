package com.example.sns.application.usecase;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.entity.Follow;
import com.example.sns.domain.follow.service.FollowReadService;
import com.example.sns.domain.member.dto.MemberDto;
import com.example.sns.domain.member.service.MemberReadService;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFollowingMembersUsecase {
    private final MemberReadService memberReadService;
    private final FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        List<FollowDto> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(FollowDto::getToMemberId).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
