package com.longjiang.stormstout.download;

import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.response.Response;
import com.longjiang.stormstout.scheduler.Scheduler;
import com.longjiang.stormstout.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author:longjiang
 * @date:下午2:29 2019/7/10
 * @Description: 下载器
 * 负责下载网页，并把下载的内容传给pipeline进行后续保存处理
 **/
public class Downloader implements Runnable {

    private Logger logger= LoggerFactory.getLogger(Downloader.class);

    private Scheduler scheduler;

    private Request request;

    public Downloader(Scheduler scheduler,Request request) {
        this.scheduler = scheduler;
        this.request=request;
    }

    @Override
    public void run() {
        logger.info("开始处理:"+request.getUrl());

        String content=HttpRequestUtil.httpGet(request.getUrl(),6000);

        logger.debug("下载完毕", request.getUrl());

        Response response = new Response(content,request);
        scheduler.addResponse(response);

    }



}
