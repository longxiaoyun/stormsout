package com.longjiang.stormstout.scheduler;

import com.longjiang.stormstout.request.Request;
import com.longjiang.stormstout.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author:longjiang
 * @date:下午5:44 2019/7/12
 * @Description:
 **/
public class Scheduler {
    private Logger logger= LoggerFactory.getLogger(Scheduler.class);

    private BlockingQueue<Response> responseQueue  = new LinkedBlockingQueue<>();
    private BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();

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
            return responseQueue.poll(10,TimeUnit.SECONDS);
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
        requests.forEach(this::addRequest);
    }


    public void clearRequest() {
        this.requestQueue.clear();
    }






}
