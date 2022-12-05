package com.example.sns.domain.post.service;

import com.example.sns.domain.post.entity.Timeline;
import com.example.sns.domain.post.repository.TimelineRepository;
import com.example.sns.util.CursorRequest;
import com.example.sns.util.PageCursor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TimelineReadService {

    private final TimelineRepository timelineRepository;

    public PageCursor<Timeline> getTimelines(Long memberId, CursorRequest cursorRequest) {
        List<Timeline> timelines = timelineRepository.cursorPageFindAllByMemberId(memberId,
            cursorRequest);
        long nextKey = timelines.stream()
            .mapToLong(Timeline::getId)
            .min().orElse(CursorRequest.NONE_KEY);
        return new PageCursor<>(cursorRequest.next(nextKey), timelines);
    }
}
