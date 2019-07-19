package com.longjiang.stormstout.pipeline;

import com.longjiang.stormstout.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author:longjiang
 * @date:下午5:03 2019/7/12
 * @Description: 数据存储,过滤等操作
 **/
public class StoresPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(StoresPipeline.class);


    @Override
    public void process(Map item, Request request) {
        logger.info("我是数据:" + item);
        logger.info("我是request:"+request.getUrl());
    }
}
