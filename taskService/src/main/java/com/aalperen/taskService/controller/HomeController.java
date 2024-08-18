package com.aalperen.taskService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/tasks/home")
	public ResponseEntity<String> taskService(){
		
		return new ResponseEntity<>("Welcome to Task Service", HttpStatus.OK);
	}

}
