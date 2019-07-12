package com.longjiang.stormstout.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @Author:longjiang
 * @date:下午3:27 2019/7/11
 * @Description:
 **/
@Component
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private Logger logger= LoggerFactory.getLogger(Sender.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息发送成功:" + correlationData);
        } else {
            logger.error("消息发送失败:" + cause);
        }

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.error(message.getMessageProperties().getCorrelationId() + " 发送失败");

    }

    //发送消息，不需要实现任何接口，供外部调用。
    public void send(String msg){

        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        logger.debug("开始发送消息 : " + msg.toLowerCase());
//        String response = rabbitTemplate.convertSendAndReceive("stormsout-mq-exchange", "for_url_queue", msg, correlationId).toString();

        this.rabbitTemplate.convertAndSend("stormsout-mq-exchange", "for_url_queue", msg,correlationId);

        logger.debug("结束发送消息 : " + msg.toLowerCase());
//        logger.debug("消费者响应 : " + response + " 消息处理完成");
    }





}
