package com.example.sns.domain.post.service;

import com.example.sns.domain.post.entity.Timeline;
import com.example.sns.domain.post.repository.TimelineRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TimelineWriteService {

    private final TimelineRepository timelineRepository;

    public void deliveryToTimeline(Long postId, List<Long> toMemberIds) {
        List<Timeline> timelines = toMemberIds.stream()
            .map((memberId) -> toTimeline(postId, memberId))
            .toList();
        timelineRepository.saveAll(timelines);
    }

    private Timeline toTimeline(Long postId, Long memberId) {
        return Timeline.builder().memberId(memberId).postId(postId).build();
    }
}
