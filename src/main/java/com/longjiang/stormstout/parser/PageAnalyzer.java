package com.longjiang.stormstout.parser;

import com.longjiang.stormstout.response.Response;
import com.longjiang.stormstout.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Author:longjiang
 * @date:下午4:48 2019/7/19
 * @Description:
 **/
public class PageAnalyzer implements Runnable {

    private Logger logger = LoggerFactory.getLogger(PageAnalyzer.class);


    private Parser parser=new ParserProcess();

    private Response response;

    public PageAnalyzer (Response response) {
        this.response=response;
    }





    @Override
    public void run() {
        Result result=parser.parse(response);
        logger.info("结果处理"+result.getItem());
        // todo 提取更多url,提取出结果并保存
    }
}
