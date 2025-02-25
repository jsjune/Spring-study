package com.example.springmysqlsharding.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ShardRoutingDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> currentShard = new ThreadLocal<>();

    public static void setCurrentShard(String shard) {
        currentShard.set(shard);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String shard = currentShard.get();
        return (shard != null) ? shard : "shard1"; // 기본값 shard1
    }
}
