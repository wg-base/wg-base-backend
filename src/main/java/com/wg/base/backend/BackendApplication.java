package com.wg.base.backend;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wg.base.backend.config.RedissonConfig;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    @ConfigurationProperties(prefix = "lock.redis")
    public RedissonConfig redissonConfig(){
        return new RedissonConfig();
    }

    @Bean
    RedissonClient redissonSingle() {
        RedissonConfig redissonConfig = redissonConfig();
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redissonConfig.getAddress())
                .setTimeout(redissonConfig.getTimeout())
                .setConnectionPoolSize(redissonConfig.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonConfig.getConnectionMinimumIdleSize());

        if(StringUtils.isNotBlank(redissonConfig.getPassword())) {
            serverConfig.setPassword(redissonConfig.getPassword());
        }
        return Redisson.create(config);
    }


}
