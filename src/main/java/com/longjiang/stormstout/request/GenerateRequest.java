//package com.longjiang.stormstout.request;
//
//import com.longjiang.stormstout.scheduler.SchdulerQueue;
//import com.longjiang.stormstout.spider.CrawlerSpider;
//
//import java.util.Map;
//
///**
// * @Author:longjiang
// * @date:下午6:48 2019/7/24
// * @Description:
// **/
//public class GenerateRequest implements Runnable {
//
//
//    private SchdulerQueue schdulerQueue;
//
//    private String url;
//    private Map<String,Object> headers;
//    private String proxy;
//
//    public GenerateRequest(SchdulerQueue q,String u,Map<String,Object> h,String p){
//        this.schdulerQueue=q;
//        this.url=u;
//        this.headers=h;
//        this.proxy=p;
//    }
//
//    @Override
//    public void run() {
//        new CrawlerSpider(schdulerQueue).make_request_from_url(url,headers,proxy);
//    }
//
//
//}
