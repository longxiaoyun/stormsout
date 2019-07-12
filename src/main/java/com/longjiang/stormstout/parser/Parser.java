package com.longjiang.stormstout.parser;

import com.longjiang.stormstout.scheduler.Sender;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:longjiang
 * @date:下午4:15 2019/7/10
 * @Description: 解析器，负责解析页面里的url，以及其他特定内容
 * 解析出的url添加到待爬取url队列
 * 解析出的网页内容传给pipeline进行存储等后续操作
 **/
public class Parser {

    private final static Logger logger = LoggerFactory.getLogger(Parser.class);

    public  void fetchUrl(String content){
        if (StringUtil.isBlank(content)) {
            logger.error ("content 不能为空");
            // 错误处理
        }
        Document doc = Jsoup.parse(content);
        Elements links = doc.select("a[href]");
        Elements medias = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        if (links.isEmpty()) {
            return;
        }

        Sender sender=new Sender();

        try {
            for(Element link:links) {
                String href=link.attr("abs:href");
                if (!StringUtil.isBlank(href)) {
                    logger.info("href=====:" + href);
                    sender.send("http://www.test.com");
                }
            }
        }catch (Exception e) {
            logger.error("eeee:"+e);
        }


//        links.stream().map((link) -> link.attr("abs:href")).forEachOrdered((String tmpUrl) -> {
//            if (tmpUrl!=null && !filterImage(tmpUrl) && !StringUtil.isBlank(tmpUrl) && !filterAssets(tmpUrl)) {
//                // 添加到url队列
//                logger.info("tmpUrl====:"+tmpUrl);
//                sender.send(tmpUrl);
//
//            }
//        });


//        medias.stream().map((media) -> media.attr("abs:src")).forEachOrdered((tmpSrc) -> {
//            if (tmpSrc!=null && !filterImage(tmpSrc) && !StringUtil.isBlank(tmpSrc) && !filterAssets(tmpSrc)) {
//                logger.info("tmpSrc=====:"+tmpSrc);
//                // 添加到url队列
//                sender.send(tmpSrc);
//            }
//
//        });
//
//
//        imports.stream().map((importLink) -> importLink.attr("abs:href")).forEachOrdered((tmpImport) -> {
//            if (tmpImport!=null && !filterImage(tmpImport) && !StringUtil.isBlank(tmpImport) && !filterAssets(tmpImport)) {
//                logger.info("tmpImport=====:"+tmpImport);
//                // 添加到url队列
//                sender.send(tmpImport);
//            }
//        });


    }


    /**
     * 过滤图片
     * @param link
     * @return
     */
    private static boolean filterImage(String link) {
        Pattern p=Pattern.compile(".*(\\.png|\\.jpg|\\.jpeg|\\.gif)$");
        Matcher m=p.matcher(link);
        return m.matches();
    }

    /**
     *  过滤js css静态文件
     * @param link
     * @return
     */
    private static boolean filterAssets(String link) {
        Pattern p=Pattern.compile(".*(\\.js|\\.css)$");
        Matcher m=p.matcher(link);
        return m.matches();
    }


}
