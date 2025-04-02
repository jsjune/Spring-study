package com.example.springcaffeine.repository;

import com.example.springcaffeine.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    @Query("select f from Feed f where f.id <= :id order by f.id desc limit 10")
    List<Feed> getFeeds(@Param("id") Long id);
    @Query("select f from Feed f order by f.id desc limit 10")
    List<Feed> getFeeds();
}
