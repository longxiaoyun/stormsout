package com.longjiang.stormstout.parser;

import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.pipeline.FileStorePipeline;
import com.longjiang.stormstout.pipeline.PageInfoBean;
import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.scheduler.SchdulerQueue;
import com.longjiang.stormstout.spider.CrawlerSpider;
import com.longjiang.stormstout.utils.ExecutorTaskUtil;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
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
        Document response=schdulerQueue.getResponse();

        Document doc = Jsoup.parse(response.html());

        PageInfoBean pageInfoBean=new PageInfoBean();
        String title=response.title();

        pageInfoBean.setUrl(response.location());

        pageInfoBean.setTitle(title);


        Elements keywords=doc.getElementsByTag("meta[name=keywords]");

        keywords.forEach(kw -> {
            String keyword=kw.attr("content");
            pageInfoBean.setKeyword(keyword);
            logger.info("关键词: "+ keyword);
        });

        Elements descs=doc.getElementsByTag("meta[name=description]");
        descs.forEach(des -> {
            String description=des.attr("content");
            pageInfoBean.setDescription(description);
            logger.info("描述: " + description);
        });

        pageInfoBean.setBody(doc.body().text());

        pageInfoBean.setHtml(doc.body().html());

        FileStorePipeline.exportExcel(pageInfoBean);


        // 添加新的url
        List<String> urls=doc.select("a").parallelStream().map(link -> link.attr("abs:href")).filter(link -> !link.trim().equals("")).collect(Collectors.toList());

        logger.info("提取到更多的url:"+urls);

        Map<String,Object> headers=new HashMap<>();
        String proxy="";

        urls.stream().filter(u -> !StringUtil.isBlank(u)).forEach(url -> {
            schdulerQueue.addRequest(new Request(url,headers,proxy));
            ExecutorTaskUtil.getInstance().execute(new Downloader(schdulerQueue));
        });


        logger.debug("---------"+schdulerQueue.hasRequest());



    }
}
