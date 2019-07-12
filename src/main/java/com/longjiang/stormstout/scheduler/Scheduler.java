package com.longjiang.stormstout.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:longjiang
 * @date:下午5:44 2019/7/12
 * @Description:
 **/
public class Scheduler {
    private Logger logger= LoggerFactory.getLogger(Scheduler.class);

    private BlockingQueue<String> queue  = new LinkedBlockingQueue<>();

    /**
     * 添加url
     * @param url
     */
    public void addUrl(String url){
        try {
            this.queue.offer(url,10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("添加 URL 出错" + e);
        }
    }

    /**
     * 添加urls列表
     * @param urls
     */
    public void addUrlList(List<String> urls) {
        urls.forEach(this::addUrl);
    }


    /**
     * 获取移除一个url
     * @return
     */
    public String pollUrl(){
        String url="";
        try {
            url=this.queue.poll(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("获取 URL 出错" + e);
        }
        return url;
    }



}
