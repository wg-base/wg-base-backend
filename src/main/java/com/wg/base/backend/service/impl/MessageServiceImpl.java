package com.wg.base.backend.service.impl;

import com.wg.base.backend.common.Constant;
import com.wg.base.backend.service.MessageService;
import com.wg.base.backend.dao.common.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean checkCanSend(String phone) {
        boolean flag = false;
        String key = Constant.REDIS_MSG + phone;
        long count = redisUtils.incr(key, 1);
        if (count == 1) {
            redisUtils.expire(key,60);
            flag = true;
        }
        return flag;
    }

    @Override
    public void sendMessage(String phone, String msg) {

    }
}
