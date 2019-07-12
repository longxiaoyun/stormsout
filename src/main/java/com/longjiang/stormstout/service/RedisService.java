package com.longjiang.stormstout.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:longjiang
 * @date:下午1:08 2019/7/11
 * @Description:
 **/
public interface RedisService {


    void set(String key,String value);

    Object pop(String key);

    List<Object> get(String key, int start, int end);

    int getSize(String key);



}
