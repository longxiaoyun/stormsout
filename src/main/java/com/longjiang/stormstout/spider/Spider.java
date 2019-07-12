package com.longjiang.stormstout.spider;

import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.parser.Parser;
import com.longjiang.stormstout.pipeline.StoresPipeline;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:longjiang
 * @date:下午5:53 2019/7/12
 * @Description:
 **/
@Service
public class Spider implements Downloader,Parser,StoresPipeline {

    @Override
    public String download(String url) {
        return null;
    }

    @Override
    public List<String> fetchUrl(String content) {
        if (StringUtil.isBlank(content)) {
            // todo 错误处理
        }
        Document doc = Jsoup.parse(content);

        return doc.select("a").parallelStream().map(link -> link.attr("abs:href")).filter(link -> !link.trim().equals("")).collect(Collectors.toList());
    }

    @Override
    public Object storeToTarget(String result) {
        return null;
    }

}
