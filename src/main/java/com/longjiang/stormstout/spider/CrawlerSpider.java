package com.longjiang.stormstout.spider;

import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.scheduler.SchdulerQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author:longjiang
 * @date:下午6:06 2019/7/24
 * @Description:
 **/
public class CrawlerSpider {

    private Logger logger = LoggerFactory.getLogger(CrawlerSpider.class);

    private SchdulerQueue schdulerQueue;

    public CrawlerSpider(SchdulerQueue q){
        this.schdulerQueue=q;
    }


    // 组装Request
    public void make_request_from_url(String url, Map<String, Object> headers, String proxy){
        logger.info("开始构造request: " + url);

        try {
            Thread.sleep(1);
            schdulerQueue.addRequest(new Request(url,headers,proxy));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
