package com.longjiang.stormstout.parser.Impl;

import com.longjiang.stormstout.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author:longjiang
 * @date:下午2:11 2019/7/12
 * @Description:
 **/
@Service
public class ParseImpl implements Parser {

    private Logger logger = LoggerFactory.getLogger(ParseImpl.class);

    @Override
    public List<String> fetchUrl(String content) {
        if (StringUtil.isBlank(content)) {
            logger.error ("content 不能为空");
            // 错误处理
        }




        Document doc = Jsoup.parse(content);
//        Elements links = doc.select("a[href]");
//        Elements medias = doc.select("[src]");
//        Elements imports = doc.select("link[href]");



        return doc.select("a").parallelStream().map(link -> link.attr("abs:href")).filter(link -> !link.trim().equals("")).collect(Collectors.toList());

//        List<String> urls=new ArrayList<>();
//
//        links.stream().map((link) -> link.attr("abs:href")).forEach((String href) -> {
//            if (!StringUtil.isBlank(href) && !isBadLink(href)) {
//                // 添加到url队列
//                logger.debug("get a url:"+href);
//                urls.add(href);
//            }
//        });
//
//
//        medias.stream().map((media) -> media.attr("abs:src")).forEachOrdered((tmpSrc) -> {
//            if (!StringUtil.isBlank(tmpSrc) && !isBadLink(tmpSrc)) {
//                logger.debug("href=====:" + tmpSrc);
//                // 添加到url队列
//                urls.add(tmpSrc);
//            }
//
//        });
//
//
//        imports.stream().map((importLink) -> importLink.attr("abs:href")).forEachOrdered((tmpImport) -> {
//            if (!StringUtil.isBlank(tmpImport) && !isBadLink(tmpImport)) {
//                logger.debug("href=====:" + tmpImport);
//                // 添加到url队列
//                urls.add(tmpImport);
//            }
//        });
//
//
//        Elements eles=doc.getElementsByTag("title");
//        Element element=eles.get(0);
//        logger.info("标题:"+element.text());
//
//        return urls;


    }


    /**
     * 过滤图片
     * @param link
     * @return
     */
    private static boolean isBadLink(String link) {
        Pattern p=Pattern.compile(".*(\\.png|\\.jpg|\\.jpeg|\\.gif|\\.js|\\.css)$");
        Matcher m=p.matcher(link);
        return m.matches();
    }
}
