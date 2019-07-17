package com.longjiang.stormstout.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weiwei
 * @date:下午5:02 2019/7/11
 * @Description: 通用线程池(单例)
 **/
public class ExecutorTaskUtil {
    private static Integer N_THREADS_NUM = 6;

    private final static int CLOSE_TIME_OUT = 5;

    private volatile static ExecutorService executorService;

    private synchronized void setNThreads(int num){
        N_THREADS_NUM = num;
    }

    public synchronized static ExecutorService getInstance(){
        if(executorService == null){
            //暂时选用固定线程池
            executorService = Executors.newFixedThreadPool(N_THREADS_NUM);
            return executorService;
        }
        return executorService;
    }

    public static void close() throws InterruptedException {
        if(executorService == null){
            return;
        }
        while (executorService.awaitTermination(CLOSE_TIME_OUT, TimeUnit.SECONDS)){
            //线程全部运行结束 关闭线程池
            executorService.shutdown();
        }
    }
}
