package com.longjiang.stormstout.scheduler;


import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.parser.Parser;

/**
 * @Author:longjiang
 * @date:下午4:09 2019/7/10
 * @Description: 负责url管理、去重、分配等等
 **/
public class Scheduler {

    public static void downloadSchdule() {
        Downloader downloader=new Downloader();
        String content=downloader.download("http://www.hao123.com");


        Parser parser=new Parser();
        parser.fetchUrl(content);
    }

    public static void main(String[] args) {
        downloadSchdule();
    }



}
