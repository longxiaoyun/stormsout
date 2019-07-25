package com.longjiang.stormstout.parser;

import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.scheduler.SchdulerQueue;
import com.longjiang.stormstout.spider.CrawlerSpider;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:longjiang
 * @date:下午6:11 2019/7/24
 * @Description:
 **/
public class PageAnalyzer implements Runnable {

    private Logger logger = LoggerFactory.getLogger(PageAnalyzer.class);

    private SchdulerQueue schdulerQueue;

    public PageAnalyzer(SchdulerQueue q){
        this.schdulerQueue=q;
    }

    @Override
    public void run() {
        String response=schdulerQueue.getResponse();

        Document doc = Jsoup.parse(response);

        String title=doc.select("title").text();
        logger.info("title: " + title);

        // 添加新的url
        List<String> urls=doc.select("a").parallelStream().map(link -> link.attr("abs:href")).filter(link -> !link.trim().equals("")).collect(Collectors.toList());

        logger.info("提取到更多的url:"+urls);

        Map<String,Object> headers=new HashMap<>();
        String proxy="";

        urls.stream().filter(u -> !StringUtil.isBlank(u)).forEach(url -> {
            schdulerQueue.addRequest(new Request(url,headers,proxy));
        });

        logger.info("---------"+schdulerQueue.hasRequest());



    }
}
