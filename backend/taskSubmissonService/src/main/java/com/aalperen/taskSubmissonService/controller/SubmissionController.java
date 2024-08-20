package com.aalperen.taskSubmissonService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aalperen.taskSubmissonService.entity.Submission;
import com.aalperen.taskSubmissonService.entity.UserDto;
import com.aalperen.taskSubmissonService.service.SubmissionService;
import com.aalperen.taskSubmissonService.service.TaskService;
import com.aalperen.taskSubmissonService.service.UserService;

@RestController
@RequestMapping("/api")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping("/submissions")
	public ResponseEntity<Submission> submitTask(@RequestParam Long taskId, @RequestParam("githubLink") String githubLink, @RequestHeader("Authorization") String jwt) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		
		Submission submission = submissionService.submitTask(taskId, githubLink, user.getId(), jwt);
		
		
		
		return new ResponseEntity<>(submission,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/submissions/{id}")
	public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		
		Submission submission = submissionService.getTaskSubmissionById(id);
		
		
		
		return new ResponseEntity<>(submission,HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/submissions")
	public ResponseEntity<List<Submission>> getAllSubmissions(@RequestHeader("Authorization") String jwt) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		
		List<Submission>submission = submissionService.getAllTaskSubmissions();
		
		
		
		return new ResponseEntity<>(submission,HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/submissions/task/{taskId}")
	public ResponseEntity<List<Submission>> getTaskSubmissions(@PathVariable Long taskId, @RequestHeader("Authorization") String jwt) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		
		List<Submission>submission = submissionService.getTaskSubmissionsByTaskId(taskId);
		
		
		
		return new ResponseEntity<>(submission,HttpStatus.OK);
		
		
	}
	
	@PutMapping("/submissions/{id}")
	public ResponseEntity<Submission> acceptOrDeclineSubmission(@PathVariable Long id,@RequestParam("status") String status, @RequestHeader("Authorization") String jwt) throws Exception{
		UserDto user = userService.getUserProfile(jwt);
		
		Submission submission = submissionService.acceptDeclineSubmission(id, status);
		
		
		
		return new ResponseEntity<>(submission,HttpStatus.OK);
		
		
	}
	
	
}
