package com.longjiang.stormstout.engine;

import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.parser.Parser;
import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.response.Response;
import com.longjiang.stormstout.response.Result;
import com.longjiang.stormstout.scheduler.Scheduler;
import com.longjiang.stormstout.spider.Spider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Author:longjiang
 * @date:上午11:30 2019/7/15
 * @Description:
 **/
public class Engine {


    private Logger logger= LoggerFactory.getLogger(Engine.class);


    private Scheduler scheduler;

    private ExecutorService executorService;

    private Spider spider;



    // 构造方法，注入spider
    public Engine (Spider spider) {
        this.spider=spider;
        this.scheduler=new Scheduler();
    }

    // 生产
    public void producer() throws InterruptedException {

        List<Request> requests = spider.getStartUrls().stream().map(spider::makeRequest).collect(Collectors.toList());
        logger.info("requests:"+requests);

        spider.getRequests().addAll(requests);
        scheduler.addRequests(requests);

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        while (scheduler.hasRequest()) {
            Request request = scheduler.nextRequest();
            executorService.execute(new Downloader(scheduler,request));
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);


        // 开始消费
        this.consumer();

    }



    public void consumer() {

        while (scheduler.hasResponse()) {
            Response response=scheduler.nextResponse();
            logger.info("准备处理response:"+response);

            Document doc = Jsoup.parse(response.getBody());
            List<String> urls=doc.select("a").parallelStream().map(link -> link.attr("abs:href")).filter(link -> !link.trim().equals("")).collect(Collectors.toList());

            logger.info("提取的url:"+urls);

            List<Request> requests = urls.stream().map(spider::makeRequest).collect(Collectors.toList());
            spider.getRequests().addAll(requests);

            String title=doc.select("title").text();

            logger.info("title:"+title);


        }

    }


    private void save() {
        // todo etl save ...
    }








}
