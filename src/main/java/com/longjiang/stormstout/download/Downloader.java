package com.longjiang.stormstout.download;

import com.longjiang.stormstout.scheduler.Sender;
import com.longjiang.stormstout.utils.HttpRequestUtil;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author:longjiang
 * @date:下午2:29 2019/7/10
 * @Description: 下载器
 * 负责下载网页，并把下载的内容传给pipeline进行后续保存处理
 **/
public class Downloader {

    private Logger logger= LoggerFactory.getLogger(Downloader.class);



    public String download(String url) {
        HttpRequestUtil httpRequestUtil=new HttpRequestUtil();

        return httpRequestUtil.httpGet(url,6000);
    }


}
