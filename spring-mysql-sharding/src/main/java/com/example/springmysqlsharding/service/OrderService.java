package com.example.springmysqlsharding.service;

import com.example.springmysqlsharding.config.ShardRoutingDataSource;
import com.example.springmysqlsharding.entity.Order;
import com.example.springmysqlsharding.utils.ShardSelector;
import com.example.springmysqlsharding.utils.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderQueryService orderQueryService;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    public void saveOrder(Order order) {
        long snowflakeId = snowflakeIdGenerator.nextId(); // ✅ Snowflake ID 생성
        String shard = ShardSelector.selectShardDatabase(snowflakeId, 3); // ✅ 샤드 결정
        ShardRoutingDataSource.setCurrentShard(shard); // ✅ 현재 요청의 샤드를 설정

        log.info("Saving order {} to {}", snowflakeId, shard);
        orderQueryService.saveOrderInTransaction(order, snowflakeId);
    }



    public Optional<Order> getOrderById(long orderId) {
        // ✅ Snowflake ID 기반으로 샤드 결정
        String shard = ShardSelector.selectShardDatabase(orderId, 3);
        ShardRoutingDataSource.setCurrentShard(shard);

        // ✅ 해당 샤드에서 Order 조회
        return orderQueryService.getOrder(orderId);
    }


}
