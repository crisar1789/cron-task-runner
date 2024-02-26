package com.rockwell.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
	
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Application.class);
		log.info("Rockwell - Get headers info");
		SpringApplication.run(Application.class, args);
	}

}
