package com.example.sns.domain.follow.repository;

import com.example.sns.domain.follow.entity.Follow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    List<Follow> findAllByFromMemberId(Long memberId);
}
