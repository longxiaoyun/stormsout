package com.longjiang.stormstout.download;


import com.longjiang.stormstout.parser.PageAnalyzer;
import com.longjiang.stormstout.scheduler.SchdulerQueue;
import com.longjiang.stormstout.spider.CrawlerSpider;
import com.longjiang.stormstout.utils.ExecutorTaskUtil;
import com.longjiang.stormstout.utils.HttpRequestUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
        logger.debug("开始下载:" + url);

//        String content = HttpRequestUtil.httpGet(url, 6000);
        try {
            //获取请求连接
            Connection con = Jsoup.connect(url);
            //请求头设置，特别是cookie设置
            con.header("Accept", "text/html, application/xhtml+xml, */*");
            con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))");
            //解析请求结果
            Document doc = con.get();

            logger.info(url +"---已下载---"+doc.title());

            schdulerQueue.addResponse(doc);

            // 继续解析
            ExecutorTaskUtil.getInstance().execute(new PageAnalyzer(schdulerQueue));


        }catch (Exception e){
            logger.error("请求出错了: " + e);
        }




    }


    @Override
    public void run() {
        download();
    }



}
