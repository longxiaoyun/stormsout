package com.longjiang.stormstout;

import com.longjiang.stormstout.engine.Engine;
import com.longjiang.stormstout.spider.Spider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StormstoutApplicationTests {






	@Test
	public void contextLoads() {

	}




    @Test
    public void testSender() throws Exception {
	    Spider spider=new Spider() {
            @Override
            protected <T> List<T> parse(String response) {
                return null;
            }

            @Override
            public List<String> getStartUrls() {
                List<String> urls=new ArrayList<>();
                urls.add("https://www.baidu.com");
                urls.add("https://www.hao123.com");
                return urls;
            }
        };
        Engine engine=new Engine(spider);
        engine.producer();

        // 开始消费
        engine.consumer();
    }


}
