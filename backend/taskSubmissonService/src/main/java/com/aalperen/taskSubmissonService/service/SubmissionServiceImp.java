package com.aalperen.taskSubmissonService.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.taskSubmissonService.entity.Submission;
import com.aalperen.taskSubmissonService.entity.TaskDto;
import com.aalperen.taskSubmissonService.entity.UserDto;
import com.aalperen.taskSubmissonService.repository.SubmissionRepository;

@Service
public class SubmissionServiceImp implements SubmissionService {
	
	@Autowired
	private SubmissionRepository submissionRepository;
	
//	@Autowired
//	private UserService userService;
	
	@Autowired
	private TaskService taskService;

	@Override
	public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception {
		
		//UserDto user = userService.getUserProfile(jwt);
		
		TaskDto task = taskService.getTaskById(taskId, jwt);
		
		if(task != null) {
			Submission submission = new Submission();
			
			submission.setGithubLink(githubLink);
			submission.setTaskId(taskId);
			submission.setSubmissionTime(LocalDateTime.now());
			submission.setUserId(userId);
			
			return submissionRepository.save(submission);
			
			
		}
		throw new Exception("Task not found");
	}

	@Override
	public Submission getTaskSubmissionById(Long submissionId) throws Exception {
		// TODO Auto-generated method stub
		return submissionRepository.findById(submissionId).orElseThrow(() -> new Exception("Task submission not found"));
		
	}

	@Override
	public List<Submission> getAllTaskSubmissions() throws Exception {
		// TODO Auto-generated method stub
		return submissionRepository.findAll();
	}

	@Override
	public List<Submission> getTaskSubmissionsByTaskId(Long taskId) throws Exception {
		// TODO Auto-generated method stub
		return submissionRepository.findByTaskId(taskId);
	}

	@Override
	public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
		
		Submission submission = getTaskSubmissionById(id);
		submission.setStatus(status);
		
		if(status.equals("ACCEPT")) {
			taskService.completeTask(submission.getTaskId());
		}
		
		
		return submissionRepository.save(submission);
	}

}
