package com.wg.base.backend.job;

import com.wg.base.backend.service.KafkaService;
import com.wg.base.backend.util.TokenUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskJobs {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskJobs.class);

    private static final String TOKEN_TEST = "TOKEN_TEST";

    private static final String KAFKA_MESSAGE = "KAFKA_MESSAGE";

    private static final String token=TokenUtils.sign("wangliheng","10");

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private KafkaService kafkaService;

    /**
     * 测试token时效性
     */
    //@Scheduled(initialDelay = 1000*60 ,fixedDelay = 1000*30)
    public void testToken(){
        RLock rLock = redissonClient.getLock(TOKEN_TEST);
        try {
            if (rLock.tryLock()) {
                LOGGER.info("测试token时效性--开始");
                boolean flag=TokenUtils.verify(token);
                LOGGER.info("flag is -- "+ flag);
                LOGGER.info("测试token时效性--结束");
            } else {
                LOGGER.info("failed to get lock {}", TOKEN_TEST);
            }
        } finally {
            rLock.unlock();
        }
    }

    /**
     * send message to kafka
     */
    @Scheduled(initialDelay = 1000*60 ,fixedDelay = 1000*30)
    public void send(){
        RLock rLock = redissonClient.getLock(KAFKA_MESSAGE);
        try {
            if (rLock.tryLock()) {
                LOGGER.info("send message to kafka --start");
                String dateTime = new Date().toString();
                kafkaService.send(dateTime);
                LOGGER.info("send message to kafka --end");
            } else {
                LOGGER.info("failed to get lock {}", KAFKA_MESSAGE);
            }
        } finally {
            rLock.unlock();
        }
    }
}
