package com.aalperen.taskSubmissonService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.aalperen.taskSubmissonService.entity.TaskDto;

@FeignClient(name="task-Service", url = "http://localhost:8083") 
public interface TaskService {
	
	@GetMapping("/api/tasks/{id}")
	public TaskDto getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception;
	
	@PutMapping("/api/tasks/complete/{id}")
	public TaskDto completeTask(@PathVariable Long id) throws Exception;

}
