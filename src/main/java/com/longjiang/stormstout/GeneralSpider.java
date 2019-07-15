package com.longjiang.stormstout;

import com.longjiang.stormstout.engine.Engine;
import com.longjiang.stormstout.spider.Spider;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
//        Spider spider=new Spider() {
//            @Override
//            protected <T> List<T> parse(String response) {
//                return null;
//            }
//
//            @Override
//            public List<String> getStartUrls() {
//                List<String> urls=new ArrayList<>();
//                urls.add("https://www.baidu.com");
//                urls.add("https://www.hao123.com");
//                return urls;
//            }
//        };
//        Engine engine=new Engine(spider);
//        try {
//            engine.producer();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected <T> List<T> parse(String response) {
        return null;
    }


    public static void main(String[] args) {
       new GeneralSpider().start_requests();
    }
}
