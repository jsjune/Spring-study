package com.example.springmysqlsharding.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShardSelector {
    public static String selectShardDatabase(long snowflakeId, int shardCount) {
        long shardId = (snowflakeId % shardCount) + 1;
        String shard = "shard" + shardId;
        log.info("Selecting shard for ID {} -> {}", snowflakeId, shard); // 🔍 로그 추가
        return shard; // shard1, shard2, shard3 중 하나 선택
    }
}
