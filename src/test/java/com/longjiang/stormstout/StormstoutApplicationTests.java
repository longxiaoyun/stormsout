package com.longjiang.stormstout;

import com.longjiang.stormstout.config.RabbitMQConfig;
import com.longjiang.stormstout.download.Downloader;
import com.longjiang.stormstout.scheduler.Sender;
import com.longjiang.stormstout.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static com.longjiang.stormstout.scheduler.Scheduler.downloadSchdule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StormstoutApplicationTests {

    @Autowired
    private RedisService redisService;


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private Sender sender;

	@Test
	public void contextLoads() {
	    String url="http://www.baidu.com";
        for(int i=0;i<10;i++) {
            sender.send(url);
//            rabbitTemplate.convertAndSend("stormsout-mq-exchange", "for_request_queue", url);

        }
	}


	@Test
    public void testDownload() {
        downloadSchdule();
    }



    @Test
    public void testSender() throws Exception {
        while(true){
            String msg = new Date().toString();
            sender.send(msg);
            Thread.sleep(1000);
        }
    }


}
