package com.longjiang.stormstout.spider;
import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.parser.PageAnalyzer;
import com.longjiang.stormstout.parser.Parser;
import com.longjiang.stormstout.parser.ParserProcess;
import com.longjiang.stormstout.pipeline.Pipeline;
import com.longjiang.stormstout.pipeline.StoresPipeline;
import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.scheduler.Scheduler;
import com.longjiang.stormstout.utils.ExecutorTaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:longjiang
 * @date:下午5:53 2019/7/12
 * @Description:
 **/
public class Spider {
    private Logger logger =LoggerFactory.getLogger(Spider.class);

    private Scheduler scheduler=new Scheduler();


    private Parser parser=new ParserProcess();


    private Pipeline pipeline=new StoresPipeline();



    private List<Request>  requests  = new ArrayList<>();

    // 添加初始url
    public Spider addUrl(String... urls) {
        for (String url : urls) {
            scheduler.addRequest(new Request(url));
        }
        return this;
    }

    // 初始url
    public Spider startRequests(List<String> urls) {
        urls.forEach(url -> {
            this.requests.add(new Request<>(url));
        });
        return this;
    }

    // 初始化
    private void _init() {
        logger.info("初始化......");

        if (parser == null) {
            logger.warn("parser引入为空");
        }

        if (scheduler == null) {
            logger.warn("scheduler为空");
        }
        if (!requests.isEmpty()) {
            for (Request request : requests) {
                scheduler.addRequest(request);
            }
            requests.clear();
        }
    }




    // 网页下载(多线程处理)
    public void crawler() throws InterruptedException {
        _init();

        while (scheduler.hasRequest()) {
            Request request = scheduler.nextRequest();
            ExecutorTaskUtil.getInstance().execute(new Downloader(scheduler,request));
        }
        ExecutorTaskUtil.close();
    }

    // web页面分析(多线程处理)
    public void analyzer() throws InterruptedException {
        while (scheduler.hasResponse()) {
            ExecutorTaskUtil.getInstance().execute(new PageAnalyzer(scheduler.nextResponse()));
        }
        ExecutorTaskUtil.close();
    }



}




















