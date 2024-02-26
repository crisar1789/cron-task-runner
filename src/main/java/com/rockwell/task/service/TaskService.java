package com.rockwell.task.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rockwell.task.object.TaskResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskService {

	@Value("${task.default.url}")
	private String url;
	
	public TaskResponse getInfo(String url) {
		this.url = url;
		return executetask();
		
	}
	
	@Scheduled(fixedRateString = "${task.scheduler.cron}")
	public TaskResponse executetask() {
		
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder data = new StringBuilder (restTemplate.getForObject(this.url, String.class));
		String headers = data.substring(0, 1000);
		
		log.info("Headers info from {}", url);
		log.info(headers);
		
		return TaskResponse.builder().info(headers).build();
	}
}
