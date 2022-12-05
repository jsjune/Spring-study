package com.example.sns.application.usecase;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.service.FollowReadService;
import com.example.sns.domain.post.dto.PostCommand;
import com.example.sns.domain.post.service.PostWriteService;
import com.example.sns.domain.post.service.TimelineWriteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {
    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    public Long execute(PostCommand command) {
        Long postId = postWriteService.create(command);
        List<Long> followerMemberIds = followReadService.getFollowers(command.memberId())
            .stream()
            .map(FollowDto::getFromMemberId)
            .toList();
        timelineWriteService.deliveryToTimeline(postId, followerMemberIds);
        return postId;
    }

}
