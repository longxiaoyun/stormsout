package com.longjiang.stormstout.scheduler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author:longjiang
 * @date:下午3:33 2019/7/11
 * @Description:
 **/
@Component
public class Receiver {
//    @RabbitListener(queues = "request_queue")
//    public String processMessageRequest(String msg) {
//        System.out.println(Thread.currentThread().getName() + " 接收到来自request_queue队列的消息：" + msg);
//        return msg.toUpperCase();
//    }

    @RabbitListener(queues = "url_queue")
    public void processMessageUrl(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自url_queue队列的消息：" + msg);
    }
}
