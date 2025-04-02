package com.example.springcaffeine.service;

import com.example.springcaffeine.dto.FeedInfoDto;
import com.example.springcaffeine.dto.FeedRequest;
import com.example.springcaffeine.entity.Feed;
import com.example.springcaffeine.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "feed", key = "#p0")
    public FeedInfoDto getFeedInfo(Long id) {
        return feedRepository.findById(id)
                .map(FeedInfoDto::fromFeed)
                .orElseThrow(() -> new IllegalArgumentException("Feed not found"));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "feeds_paging", key = "'allFeeds::' + #p0")
    public List<FeedInfoDto> getAllFeeds(Long id) {
        return id == null ? feedRepository.getFeeds()
                .stream()
                .map(FeedInfoDto::fromFeed)
                .toList() : feedRepository.getFeeds(id)
                .stream()
                .map(FeedInfoDto::fromFeed)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "feeds_paging", allEntries = true)
    public FeedInfoDto saveFeed(FeedRequest req) {
        Feed savedFeed = feedRepository.save(Feed.of(req.title(), req.content()));
        return FeedInfoDto.fromFeed(savedFeed);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "feed", key = "#p0"),
            @CacheEvict(value = "feeds_paging", allEntries = true)
    })
    public FeedInfoDto updateFeed(Long id, FeedRequest req) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feed not found"));
        feed.update(req.title(), req.content());
        return FeedInfoDto.fromFeed(feed);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "feed", key = "#p0"),
            @CacheEvict(value = "feeds_paging", allEntries = true)
    })
    public void deleteFeed(Long id) {
        feedRepository.deleteById(id);
    }
}
