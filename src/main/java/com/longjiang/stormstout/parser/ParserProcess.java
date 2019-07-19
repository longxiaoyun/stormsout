package com.longjiang.stormstout.parser;

import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.response.Response;
import com.longjiang.stormstout.response.Result;
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
 * @date:下午2:38 2019/7/19
 * @Description: 实现解析页面接口
 **/
public class ParserProcess implements Parser {

    private Logger logger = LoggerFactory.getLogger(ParserProcess.class);

    @Override
    public Result parse(Response response) {

        Result<Object> result   = new Result<>();
        Map<String,Object> map=new HashMap<>();

        logger.info("准备处理response:"+response);

        Document doc = Jsoup.parse(response.getBody());

        String title=doc.select("title").text();

        logger.info("title:"+title);

        // 获取网站描述和关键词
        doc.select("meta[name=description]").stream().filter(e -> !StringUtil.isBlank(e.attr("content"))).forEach(ele -> {
            String content=ele.attr("content");
            logger.info("网页描述：=====" + content);
        });

        doc.select("meta[property=description]").stream().filter(e -> StringUtil.isBlank(e.attr("content"))).forEach(ele -> {
            String s = ele.attr("content");
            logger.info("描述:"+s);
        });



        // 添加新的url
        List<String> urls=doc.select("a").parallelStream().map(link -> link.attr("abs:href")).filter(link -> !link.trim().equals("")).collect(Collectors.toList());

        logger.info("提取到更多的url:"+urls);

        urls.stream().filter(u -> !StringUtil.isBlank(u)).forEach(url -> {
            result.addRequest(new Request(url));
        });

        result.setItem(map);

        return result;

    }


}
