package com.example.sns.domain.post.repository;


import static com.example.sns.domain.post.entity.QPost.post;

import com.example.sns.domain.post.dto.DailyPostCount;
import com.example.sns.domain.post.dto.DailyPostCountRequest;
import com.example.sns.domain.post.entity.Post;
import com.example.sns.util.CursorRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<DailyPostCount> groupByCreateDate(DailyPostCountRequest request) {
        List<DailyPostCount> result = queryFactory
            .select(Projections.constructor(DailyPostCount.class,
                post.memberId, post.createdDate, post.id.count()))
            .from(post)
            .where(post.memberId.eq(request.memberId())
                .and(post.createdDate.between(request.firstDate(), request.lastDate())))
            .groupBy(post.memberId, post.createdDate)
            .fetch();

        return result;
    }

    @Override
    public void bulkInsert(List<Post> posts) {
        String sql = String.format("""
                INSERT INTO `%s` (contents, created_date, created_at, member_id)
                VALUES (:contents, :createdDate, :createdAt, :memberId)
                """, "post");
        SqlParameterSource[] params = posts
            .stream()
            .map(BeanPropertySqlParameterSource::new)
            .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }

    @Override
    public List<Post> cursorPageFindAllByMemberId(Long memberId, CursorRequest cursorRequest) {
        List<Post> result = queryFactory
            .selectFrom(post)
            .where(post.memberId.eq(memberId), idLessThan(cursorRequest.key()))
            .orderBy(post.id.desc())
            .limit(cursorRequest.size())
            .fetch();
        return result;
    }

    @Override
    public List<Post> cursorPageFindAllByInMemberId(List<Long> memberId,
        CursorRequest cursorRequest) {
        List<Post> result = queryFactory
            .selectFrom(post)
            .where(post.memberId.in(memberId), idLessThan(cursorRequest.key()))
            .orderBy(post.id.desc())
            .limit(cursorRequest.size())
            .fetch();
        return result;
    }

    private BooleanExpression idLessThan(Long key) {
        return key != null ? post.id.lt(key) : null;
    }
}
