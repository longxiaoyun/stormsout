package com.longjiang.stormstout.download;


import com.longjiang.stormstout.scheduler.SchdulerQueue;
import com.longjiang.stormstout.spider.CrawlerSpider;
import com.longjiang.stormstout.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;


/**
 * @Author:longjiang
 * @date:下午2:29 2019/7/10
 * @Description: 下载器
 * 负责下载网页，并把下载的内容传给pipeline进行后续保存处理
 **/
public class Downloader implements Runnable {
    private Logger logger= LoggerFactory.getLogger(Downloader.class);

    private SchdulerQueue schdulerQueue;

    public Downloader(SchdulerQueue q){
        this.schdulerQueue=q;
    }


    public void download() {
        String url=schdulerQueue.getRequest().getUrl();
        logger.info("生产者开始处理:" + url);

        String content = HttpRequestUtil.httpGet(url, 6000);

        schdulerQueue.addResponse(content);

        logger.debug("下载完毕:"+content);


    }


    @Override
    public void run() {
        download();
    }



}
