package com.longjiang.stormstout.scheduler;



import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.Sink;
import com.longjiang.stormstout.request.Request;
import org.jsoup.nodes.Document;
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
//    private BlockingQueue<String> responseQueue=new LinkedBlockingQueue<>();
    private BlockingQueue<Document> responseQueue=new LinkedBlockingQueue<>();


    public void addRequest(Request request) {
        try {
            if (!filter(request.getUrl())) {
                this.requestQueue.put(request);
            }else {
                logger.info("url已爬取!");
            }
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




    public void addResponse(Document response){
        try {
            this.responseQueue.put(response);
        } catch (InterruptedException e) {
            logger.info("向调度器添加 Response 出错"+e);
        }
    }

    public Document getResponse(){
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



    private boolean filter(String url) {
        // expectedInsertions 预期插入的数据量
        int expectedInsertions=100000;
        // falsePositionProbability 预期的误报率
        double falsePositionProbability=0.1;
        BloomFilter<String> bloomFilter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(String s, Sink sink) {
                sink.putString(s, Charsets.UTF_8);
            }
        },expectedInsertions,falsePositionProbability);

        if (bloomFilter.mightContain(url)){
            return true;
        }else {
            bloomFilter.put(url);
            return false;
        }

    }



}
