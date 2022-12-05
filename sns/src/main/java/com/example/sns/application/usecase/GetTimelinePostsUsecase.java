package com.example.sns.application.usecase;

import com.example.sns.domain.follow.dto.FollowDto;
import com.example.sns.domain.follow.entity.Follow;
import com.example.sns.domain.follow.service.FollowReadService;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.entity.Timeline;
import com.example.sns.domain.post.service.PostReadService;
import com.example.sns.domain.post.service.TimelineReadService;
import com.example.sns.util.CursorRequest;
import com.example.sns.util.PageCursor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetTimelinePostsUsecase {
    private final FollowReadService followReadService;
    private final PostReadService postReadService;
    private final TimelineReadService timelineReadService;

    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        List<FollowDto> followings = followReadService.getFollowings(memberId);
        List<Long> followingMemberIds = followings.stream().map(FollowDto::getToMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeTimeLine(Long memberId, CursorRequest cursorRequest) {
        PageCursor<Timeline> timelines = timelineReadService.getTimelines(memberId, cursorRequest);
        List<Long> postIds = timelines.body().stream().map(Timeline::getPostId).toList();
        List<Post> posts = postReadService.getPosts(postIds);
        return new PageCursor<Post>(timelines.nextCursorRequest(),posts);
    }
}
