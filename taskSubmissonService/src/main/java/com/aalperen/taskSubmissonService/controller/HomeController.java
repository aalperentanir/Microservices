package com.aalperen.taskSubmissonService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/task-submission/home")
	public String homeController() {
		return "Hello Task Submission Service";
	}

}
