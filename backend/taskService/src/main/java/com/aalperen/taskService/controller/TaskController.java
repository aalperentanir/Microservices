package com.aalperen.taskService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.taskService.entity.Task;
import com.aalperen.taskService.entity.UserDto;
import com.aalperen.taskService.enums.TaskStatus;
import com.aalperen.taskService.service.TaskService;
import com.aalperen.taskService.service.UserService;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	

	
	
	@PostMapping("/tasks")
	public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception{
		
		UserDto user = userService.getUserProfile(jwt);
		
		Task createdTask = taskService.createTask(task,user.getRole());
		
		
		return new ResponseEntity<>(createdTask,HttpStatus.CREATED);
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception{
		
		UserDto user = userService.getUserProfile(jwt);
		
		Task task = taskService.getTaskById(id);
		
		
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
	@GetMapping("/tasks/user")
	public ResponseEntity<List<Task>> getAssignedUsersTask(@RequestParam(required = false) TaskStatus status, @RequestHeader("Authorization") String jwt) throws Exception{
		
		UserDto user = userService.getUserProfile(jwt);
		
		List<Task> tasks = taskService.assignedUsersTask(user.getId(), status);
		
		
		return new ResponseEntity<>(tasks,HttpStatus.OK);
	}
	
	
	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false) TaskStatus status, @RequestHeader("Authorization") String jwt) throws Exception{
		
		UserDto user = userService.getUserProfile(jwt);
		
		List<Task> tasks = taskService.getAllTask(status);
		
		
		return new ResponseEntity<>(tasks,HttpStatus.OK);
	}
	
	@PutMapping("/tasks/{id}/user/{userId}/assigned")
	public ResponseEntity<Task> assignedTaskToUser(@PathVariable Long id, 
			@PathVariable Long userId, 
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		UserDto user = userService.getUserProfile(jwt);
		
		Task task = taskService.assignedToUser(userId, id);
		
		
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
	
	@PutMapping("/tasks/update/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, 
			@RequestBody Task req,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		UserDto user = userService.getUserProfile(jwt);
		
		Task task = taskService.updateTask(id, req, user.getId());
		
		
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
	@PutMapping("/tasks/complete/{id}")
	public ResponseEntity<Task> completeTask(@PathVariable Long id) throws Exception{
		
		
		Task task = taskService.completeTask(id);
		
		
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) throws Exception{
		
		
		taskService.deleteTask(id);
		
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
