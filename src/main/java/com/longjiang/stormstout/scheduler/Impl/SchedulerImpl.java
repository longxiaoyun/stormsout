package com.longjiang.stormstout.scheduler.Impl;

import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.parser.Parser;
import com.longjiang.stormstout.scheduler.Sender;
import com.longjiang.stormstout.scheduler.SpiderScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.scheduler.Scheduler;

import java.util.List;

/**
 * @Author:longjiang
 * @date:下午3:41 2019/7/12
 * @Description:
 **/
public class SchedulerImpl implements SpiderScheduler {

    private Logger logger= LoggerFactory.getLogger(SchedulerImpl.class);

    private Parser parser;

    private Downloader downloader;

    private Sender sender;




    @Override
    public void StartCrawl(String seed) {
        String content=downloader.download(seed);

        List<String> urls=parser.fetchUrl(content);



    }
}
