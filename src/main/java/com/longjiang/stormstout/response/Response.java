package com.longjiang.stormstout.response;

import com.longjiang.stormstout.request.Request;
import lombok.Getter;

import java.io.InputStream;

/**
 * @Author:longjiang
 * @date:上午11:03 2019/7/15
 * @Description:
 **/
public class Response {

    @Getter
    private String  body;

    @Getter
    private Request request;

    public Response(String resp,Request request) {
        this.body=resp;
        this.request=request;
    }

}
