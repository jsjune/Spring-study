package com.example.sns.domain.post.repository;

import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.entity.Timeline;
import com.example.sns.util.CursorRequest;
import java.util.List;

public interface TimelineRepositoryCustom {
    List<Timeline> cursorPageFindAllByMemberId(Long memberId, CursorRequest cursorRequest);

    List<Timeline> cursorPageFindAllByMemberId(List<Long> memberId, CursorRequest cursorRequest);
}
