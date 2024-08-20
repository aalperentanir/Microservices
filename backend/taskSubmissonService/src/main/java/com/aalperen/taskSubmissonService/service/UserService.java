package com.aalperen.taskSubmissonService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.aalperen.taskSubmissonService.entity.UserDto;



@FeignClient(name="user-Service", url = "http://localhost:8082")
public interface UserService {
	
	@GetMapping("/api/users/profile")
	UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
