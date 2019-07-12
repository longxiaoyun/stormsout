package com.longjiang.stormstout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.longjiang.stormstout.scheduler.Scheduler.downloadSchdule;


@SpringBootApplication
public class StormstoutApplication {


	public static void main(String[] args) {
		SpringApplication.run(StormstoutApplication.class, args);
	}

}
