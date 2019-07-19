package com.longjiang.stormstout.parser;

import com.longjiang.stormstout.response.Result;
import com.longjiang.stormstout.response.Response;

import java.util.Map;

/**
 * @Author:longjiang
 * @date:下午4:15 2019/7/10
 * @Description: 解析页面的接口
 **/
public interface Parser<T> {

    public Result<T> parse(Response response);


}

