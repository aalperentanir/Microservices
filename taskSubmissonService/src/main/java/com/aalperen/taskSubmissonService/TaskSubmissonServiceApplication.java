package com.aalperen.taskSubmissonService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskSubmissonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSubmissonServiceApplication.class, args);
	}

}
