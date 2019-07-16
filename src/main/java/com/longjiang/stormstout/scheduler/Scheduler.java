package com.longjiang.stormstout.scheduler;

import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:longjiang
 * @date:下午5:44 2019/7/12
 * @Description:
 **/
public class Scheduler {
    private Logger logger= LoggerFactory.getLogger(Scheduler.class);

    private BlockingQueue<Response> responseQueue  = new LinkedBlockingQueue<>();
    private BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();

    // 请求过滤条件
    private String regex=".*";

    private BloomFilter bloomFilter;

    public Scheduler() {
        this.bloomFilter=new BloomFilter();
    }



    public void addResponse(Response response) {
        try {
            this.responseQueue.offer(response,10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("向调度器添加 Response 出错", e);
        }
    }

    public boolean hasResponse() {
        return responseQueue.size() > 0;
    }

    public Response nextResponse() {
        try {
            Response response= responseQueue.poll(10,TimeUnit.SECONDS);
            // 添加到布隆过滤器，表示该url已经爬取过不在重复爬取
            bloomFilter.add(response.getRequest().getUrl());
            return response;
        } catch (InterruptedException e) {
            logger.error("从调度器获取 Response 出错", e);
            return null;
        }
    }



    public void addRequest(Request request) {
        try {
            this.requestQueue.offer(request,10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("向调度器添加 Request 出错", e);
        }
    }

    public boolean hasRequest() {
        return requestQueue.size()>0;
    }

    public Request nextRequest() {
        try {
            return requestQueue.poll(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("从调度器获取 Request 出错", e);
            return null;
        }
    }

    public void addRequests(List<Request> requests) {

        Pattern p = Pattern.compile(regex);
        // 添加到request队列，并判断是否已经爬取过了，爬取过的就不再添加
        // 再过滤掉预设值的，rule  ，意思是只爬取特定的url
        requests.stream()
                .filter(req -> !bloomFilter.contain(req.getUrl()))
                .filter(r -> p.matcher(r.getUrl()).matches())
                .forEach(this::addRequest);

    }


    public void clearRequest() {
        this.requestQueue.clear();
    }






}
