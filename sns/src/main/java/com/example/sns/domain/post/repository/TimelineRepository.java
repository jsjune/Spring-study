package com.example.sns.domain.post.repository;

import com.example.sns.domain.post.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimelineRepository extends JpaRepository<Timeline,Long>, TimelineRepositoryCustom {

}
