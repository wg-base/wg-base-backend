package com.wg.base.backend.job;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskJobs {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskJobs.class);

    public static final String TOKEN_TEST = "TOKEN_TEST";

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 测试token时效性
     */
    @Scheduled(initialDelay = 1000*60 ,fixedDelay = 1000*60*10)
    public void synRealFlow(){
        RLock rLock = redissonClient.getLock(TOKEN_TEST);
        try {
            if (rLock.tryLock()) {
                LOGGER.info("测试token时效性--开始");

                LOGGER.info("测试token时效性--结束");
            } else {
                LOGGER.info("failed to get lock {}", TOKEN_TEST);
            }
        } finally {
            rLock.unlock();
        }
    }
}
