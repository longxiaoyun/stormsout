package com.longjiang.stormstout;


import com.longjiang.stormstout.spider.Spider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author:longjiang
 * @date:下午4:26 2019/7/15
 * @Description:
 **/
public class GeneralSpider extends Spider {

    private Logger logger= LoggerFactory.getLogger(GeneralSpider.class);


    // 启动入口
    public static void main(String[] args) throws InterruptedException {

        Spider spider=new Spider();
        spider.addUrl("http://www.hao123.com/");

        // 下载
        spider.crawler();

        // 页面分析
        spider.analyzer();

    }














}
