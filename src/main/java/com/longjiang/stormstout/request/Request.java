package com.longjiang.stormstout.request;

import lombok.Data;

import java.util.Map;

/**
 * @Author:longjiang
 * @date:下午6:03 2019/7/24
 * @Description:
 **/
@Data
public class Request {
    private String url;

    private Map<String,Object> headers;

    private String proxy;

    // 实现构造方法
    public Request(String url,Map<String,Object>  headers,String proxy) {
        this.url=url;
        this.headers=headers;
        this.proxy=proxy;
    }
}
