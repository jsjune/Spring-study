package com.example.sns.domain.post.repository;

import static com.example.sns.domain.post.entity.QPost.post;
import static com.example.sns.domain.post.entity.QTimeline.*;

import com.example.sns.domain.post.entity.Post;
import com.example.sns.domain.post.entity.QTimeline;
import com.example.sns.domain.post.entity.Timeline;
import com.example.sns.util.CursorRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TimelineRepositoryImpl implements TimelineRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Timeline> cursorPageFindAllByMemberId(Long memberId, CursorRequest cursorRequest) {
        List<Timeline> result = queryFactory
            .selectFrom(timeline)
            .where(timeline.memberId.eq(memberId), idLessThan(cursorRequest.key()))
            .orderBy(timeline.id.desc())
            .limit(cursorRequest.size())
            .fetch();
        return result;
    }

    @Override
    public List<Timeline> cursorPageFindAllByMemberId(List<Long> memberId, CursorRequest cursorRequest) {
        List<Timeline> result = queryFactory
            .selectFrom(timeline)
            .where(timeline.memberId.in(memberId), idLessThan(cursorRequest.key()))
            .orderBy(timeline.id.desc())
            .limit(cursorRequest.size())
            .fetch();
        return result;
    }

    private BooleanExpression idLessThan(Long key) {
        return key != null ? timeline.id.lt(key) : null;
    }
}
