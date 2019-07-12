package com.longjiang.stormstout.service.Impl;

import com.longjiang.stormstout.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:longjiang
 * @date:下午1:18 2019/7/11
 * @Description:
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public Object pop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public List<Object> get(String key, int start, int end) {
        return redisTemplate.opsForList().range(key,start,end);
    }

    @Override
    public int getSize(String key) {
        return redisTemplate.opsForList().size(key).intValue();
    }
}
