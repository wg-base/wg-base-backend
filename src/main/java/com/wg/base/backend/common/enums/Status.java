package com.wg.base.backend.common.enums;

import java.util.concurrent.TimeUnit;

public abstract class Status {
    /**
     * 过期时间相关枚举
     */
    public static enum ExpireEnum{

        UNREAD_MSG(30L, TimeUnit.DAYS);//未读消息的有效期为30天

        private Long time;//过期时间

        private TimeUnit timeUnit;//时间单位

        ExpireEnum(Long time, TimeUnit timeUnit) {
            this.time = time;
            this.timeUnit = timeUnit;
        }

        public Long getTime() {
            return time;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }
    }
}
