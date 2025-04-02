package com.example.springcaffeine.config;

import com.example.springcaffeine.dto.FeedInfoDto;
import com.example.springcaffeine.utils.FeedRepositoryHolder;
import com.github.benmanes.caffeine.cache.CacheLoader;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public enum CacheType {
    FEED("feed", key -> {
        return FeedInfoDto.fromFeed(
                FeedRepositoryHolder.get().findById((Long) key).orElseThrow()
        );
    }),
    FEEDS_PAGING("feeds_paging", key -> {
        return FeedInfoDto.fromFeed(
                FeedRepositoryHolder.get().findById((Long) key).orElseThrow()
        );
    });

    private final String name;
    private final Integer expireAfterWrite;
    private final Integer maximumSize;
    private final CacheLoader<Object, Object> loader;

    CacheType(String name, CacheLoader<Object, Object> cacheLoader) {
        this.name = name;
        this.expireAfterWrite = ConstConfig.getTtlWithJitter();
        this.maximumSize = ConstConfig.DEFAULT_MAX_SIZE;
        this.loader = cacheLoader;
    }

    static class ConstConfig {
        static final Integer DEFAULT_TTL_SEC = 600;
        static final Integer DEFAULT_MAX_SIZE = 10240;

        static int getTtlWithJitter() {
            int jitter = ThreadLocalRandom.current().nextInt(30);
            return jitter + DEFAULT_TTL_SEC;
        }
    }
}
