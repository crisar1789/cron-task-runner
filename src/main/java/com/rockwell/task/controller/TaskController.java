package com.rockwell.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.rockwell.task.service.TaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/headers")
@Api(tags = "Rockwell", produces = "application/json")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@ApiOperation(value = "Cron Task: get headers info", tags = "cron-task")
	@GetMapping()
	public ResponseEntity<Object> getInformation(@RequestParam String url) {
		
		ResponseEntity<Object> response = null;
		
		try {
			response = new ResponseEntity<>(service.getInfo(url), HttpStatus.OK);
		} catch(IllegalArgumentException e) {
			log.error("Invalid url: ", e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch(ResourceAccessException e) {
			log.error("Web site not found: ", e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch(NullPointerException e) {
			log.error("Url not acceptable: ", e);
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		
		return response;
		//return new ResponseEntity<>(TaskResponse.builder().info("Seuccess!").build(), HttpStatus.OK);
		 
	}

}
