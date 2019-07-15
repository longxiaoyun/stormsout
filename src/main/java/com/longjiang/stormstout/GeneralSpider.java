package com.longjiang.stormstout;

import com.longjiang.stormstout.engine.Engine;
import com.longjiang.stormstout.spider.Spider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:longjiang
 * @date:下午4:26 2019/7/15
 * @Description:
 **/
public class GeneralSpider extends Spider {


    // 启动入口方法
    private void start_requests() {
        List<String> urls=new ArrayList<>();
        urls.add("http://www.baidu.com");
        urls.add("http://www.hao123.com");
        this.startUrls.addAll(urls);

    }

    @Override
    protected <T> List<T> parse(String response) {
        return null;
    }


    public static void main(String[] args) {
       new GeneralSpider().start_requests();
    }
}
