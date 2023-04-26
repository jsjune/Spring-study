package com.example.mysqlmasterslave;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RequiredArgsConstructor
public class RoutingDataSource extends AbstractRoutingDataSource {
    private final DataSourceKey dataSourceKey;

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if (isReadOnly) {
            logger.info("Connection Slave");
            return dataSourceKey.getDefaultSlaveKey();
        } else {
            logger.info("Connection Master");
            return dataSourceKey.getMasterKey();
        }
    }
}
