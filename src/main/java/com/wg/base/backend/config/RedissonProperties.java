package com.wg.base.backend.config;

import lombok.Data;

@Data
public class RedissonProperties {

    private int timeout = 3000;
    private String address;
    private String password;
    private int connectionPoolSize = 64;
    private int connectionMinimumIdleSize = 10;
    private int slaveConnectionPoolSize = 250;
    private int masterConnectionPoolSize = 250;
    private String[] sentinelAddresses;
}
