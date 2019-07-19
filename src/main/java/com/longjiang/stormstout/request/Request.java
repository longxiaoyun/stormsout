package com.longjiang.stormstout.request;

import com.longjiang.stormstout.parser.Parser;
import lombok.Getter;

/**
 * @Author:longjiang
 * @date:上午10:24 2019/7/15
 * @Description:
 **/
@Getter
public class Request<T> {
    private String url;

//    private Object headers;
//
//    private Object proxy;
//
//    private Object meta;
//
//    private Object cookies;



    // request请求完了后的回调函数
    private Parser parser;



    public Request(String url) {
        this.url=url;
        this.parser=parser;
    }




}
