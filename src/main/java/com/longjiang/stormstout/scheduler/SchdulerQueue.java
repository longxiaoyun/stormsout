package com.longjiang.stormstout.scheduler;

import com.longjiang.stormstout.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author:longjiang
 * @date:下午6:05 2019/7/24
 * @Description:
 **/
public class SchdulerQueue {
    private Logger logger = LoggerFactory.getLogger(SchdulerQueue.class);


    private BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<String> responseQueue=new LinkedBlockingQueue<>();

    public void addRequest(Request request) {
        try {
            this.requestQueue.put(request);
        } catch (InterruptedException e) {
            System.out.println("向调度器添加 Request 出错"+e);
        }
    }

    public boolean hasRequest() {
        return requestQueue.size()>0;
    }

    // 获取一个request
    public Request getRequest() {
        try {
            return requestQueue.take();
        } catch (InterruptedException e) {
            logger.info("从调度器获取 Request 出错"+e);
            return null;
        }
    }

    public void addRequests(List<Request> requests) {
        requests.forEach(this::addRequest);

    }


    public void clearRequest() {
        this.requestQueue.clear();
    }




    public void addResponse(String response){
        try {
            this.responseQueue.put(response);
        } catch (InterruptedException e) {
            logger.info("向调度器添加 Response 出错"+e);
        }
    }

    public String getResponse(){
        try {
            return responseQueue.take();
        } catch (InterruptedException e) {
            logger.info("从调度器获取 Response 出错"+e);
            return null;
        }
    }

    public boolean hasResponse() {
        return responseQueue.size()>0;
    }


}
