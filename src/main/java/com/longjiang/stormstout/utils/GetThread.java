package com.longjiang.stormstout.utils;

import com.longjiang.stormstout.parser.Parser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author:longjiang
 * @date:下午3:08 2019/7/10
 * @Description:  即使HttpClient的实例是线程安全的，可以被多个线程共享访问，但是仍旧推荐每个线程都要有自己专用实例的HttpContext
 **/
public  class GetThread extends Thread {


    private final CloseableHttpClient httpClient;
    private final HttpContext context;
    private final HttpGet httpget;

    public GetThread(CloseableHttpClient httpClient, HttpGet httpget) {
        this.httpClient = httpClient;
        this.context = HttpClientContext.create();
        this.httpget = httpget;
    }

    @Override
    public void run() {
        try {
            try (CloseableHttpResponse response = httpClient.execute(httpget, context)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String content = EntityUtils.toString(entity, "utf-8");
//                    System.out.println("content====="+content);
//                    Parser.fetchUrl(content);
                    new Parser().fetchUrl(content);
                }
            }
        } catch (IOException ex) {
            // Handle I/O errors
        }
    }
}
