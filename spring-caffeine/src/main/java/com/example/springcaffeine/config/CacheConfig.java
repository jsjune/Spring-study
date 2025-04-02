package com.example.springcaffeine.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caches = Arrays.stream(CacheType.values())
                .map(cache -> {
                    LoadingCache<Object, Object> loadingCache;
                    if (cache.getName().equals(CacheType.FEED.getName())) {
                        loadingCache = Caffeine.newBuilder()
                                .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.SECONDS)
                                .maximumSize(cache.getMaximumSize())
                                .recordStats()
                                .build(cache.getLoader());
                    } else {
                        loadingCache = Caffeine.newBuilder()
                                .expireAfterWrite(cache.getExpireAfterWrite(), TimeUnit.SECONDS)
                                .refreshAfterWrite(5, TimeUnit.SECONDS)
                                .maximumSize(cache.getMaximumSize())
                                .recordStats()
                                .build(cache.getLoader());
                    }

                    return new CaffeineCache(cache.getName(), loadingCache, false);
                })
                .toList();
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
