package com.longjiang.stormstout;

import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.parser.PageAnalyzer;
import com.longjiang.stormstout.pipeline.FileStorePipeline;
import com.longjiang.stormstout.scheduler.SchdulerQueue;
import com.longjiang.stormstout.spider.CrawlerSpider;
import com.longjiang.stormstout.utils.ExecutorTaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:longjiang
 * @date:下午6:15 2019/7/24
 * @Description:
 * Downloader 生产者
 * PageAnalyzer 消费者
 **/
public class Run {

    private static Logger logger= LoggerFactory.getLogger(Run.class);

    public static void main(String[] args) {
        SchdulerQueue scheduler=new SchdulerQueue();

        String url="http://www.hao123.com";
        Map<String,Object> headers=new HashMap<>();
        String proxy="";


        try {
            // 初始化生成Request
            new CrawlerSpider(scheduler).make_request_from_url(url, headers, proxy);

            // 开始下载
            ExecutorTaskUtil.getInstance().execute(new Downloader(scheduler));

            // 开始解析
            ExecutorTaskUtil.getInstance().execute(new PageAnalyzer(scheduler));


            // 关闭线程池
            ExecutorTaskUtil.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("Producer and Consumer has been started");


    }


}
