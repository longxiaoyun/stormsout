package com.longjiang.stormstout.spider;

import com.longjiang.stormstout.parser.Parser;
import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.response.Response;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:longjiang
 * @date:下午5:53 2019/7/12
 * @Description:
 **/
@Data
public abstract class Spider {

    protected List<String>   startUrls = new ArrayList<>();
    protected List<Request>  requests  = new ArrayList<>();


    public Spider startUrls(String... urls) {
        this.startUrls.addAll(Arrays.asList(urls));
        return this;
    }

    /**
     * 构建一个Request
     */
    public <T> Request<T> makeRequest(String url) {
        return new Request<>(url);
    }



    /**
     * 解析 DOM
     */
    protected abstract <T> List<T> parse(String response);


}
