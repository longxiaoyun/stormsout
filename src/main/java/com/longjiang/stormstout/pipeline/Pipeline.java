package com.longjiang.stormstout.pipeline;

import com.longjiang.stormstout.request.Request;

import java.util.Map;

/**
 * @Author:longjiang
 * @date:下午3:43 2019/7/19
 * @Description:
 **/
public interface Pipeline<K,V> {

    void process(Map<K,V> item, Request request);
}
