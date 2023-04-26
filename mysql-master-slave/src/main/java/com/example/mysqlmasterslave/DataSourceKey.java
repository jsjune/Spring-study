package com.example.mysqlmasterslave;

import org.springframework.stereotype.Component;

@Component
public class DataSourceKey {
    private static final String MASTER_KEY = "master";
    private static final String SLAVE_KEY = "slave";

    private static final String INDENT = "_";
    private static final int DEFAULT_SLAVE_NUMBER = 1;


    public String getMasterKey() {
        return MASTER_KEY;
    }

    public String getSlaveKeyByNumber(int idx) {
        return SLAVE_KEY + INDENT + idx;
    }

    public String getDefaultSlaveKey() {
        return SLAVE_KEY + INDENT + DEFAULT_SLAVE_NUMBER;
    }
}
