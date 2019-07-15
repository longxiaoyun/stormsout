package com.longjiang.stormstout;

import com.longjiang.stormstout.engine.Engine;
import com.longjiang.stormstout.scheduler.Scheduler;
import com.longjiang.stormstout.spider.Spider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class StormstoutApplication {


	public static void main(String[] args) {
		SpringApplication.run(StormstoutApplication.class, args);
	}



}
