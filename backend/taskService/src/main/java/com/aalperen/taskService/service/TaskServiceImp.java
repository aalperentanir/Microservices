package com.aalperen.taskService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aalperen.taskService.entity.Task;
import com.aalperen.taskService.enums.TaskStatus;
import com.aalperen.taskService.repository.TaskRepository;

@Service
public class TaskServiceImp  implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task createTask(Task task, String requesterRole) throws Exception {
		
		if(!requesterRole.equals("ADMIN")) {
			throw new Exception("Only admin create task");
		}
		
		task.setStatus(TaskStatus.PENDING);
		task.setCreatedAt(LocalDateTime.now());
		
		return taskRepository.save(task);
	}

	@Override
	public Task getTaskById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return taskRepository.findById(id).orElseThrow(()-> new Exception("Task not found"));
	}

	@Override
	public List<Task> getAllTask(TaskStatus status) {
		List<Task> allTasks =  taskRepository.findAll();
		
		List<Task> filteredTasks = allTasks.stream().filter(
				task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))
				.collect(Collectors.toList());
		
		
		return filteredTasks;
	}

	@Override
	public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
		
		Task existingTask = getTaskById(id);
		
		if(updatedTask.getTitle() != null) {
			existingTask.setTitle(updatedTask.getTitle());
		}
		
		if(updatedTask.getDescription() != null) {
			existingTask.setDescription(updatedTask.getDescription());
		}
		
		if(updatedTask.getStatus() != null) {
			existingTask.setStatus(updatedTask.getStatus());
		}
		
		if(updatedTask.getDeadLine() != null) {
			existingTask.setDeadLine(updatedTask.getDeadLine());
		}
		
		if(updatedTask.getImage() != null) {
			existingTask.setImage(updatedTask.getImage());
		}
		
		
		return taskRepository.save(existingTask);
	}

	@Override
	public void deleteTask(Long id) throws Exception {
		
		getTaskById(id);
		
		taskRepository.deleteById(id);
		
		
	}

	@Override
	public Task assignedToUser(Long userId, Long taskId) throws Exception {
		
		Task task = getTaskById(taskId);
		task.setAssignedUserId(userId);
		task.setStatus(TaskStatus.DONE);
		
		
		return taskRepository.save(task);
	}

	@Override
	public List<Task> assignedUsersTask(Long userId, TaskStatus status) {
		
		List<Task> allTasks = taskRepository.findByAssignedUserId(userId);
		
		List<Task> filteredTasks = allTasks.stream().filter(
				task -> status == null || task.getStatus().name().equalsIgnoreCase(status.toString()))
				.collect(Collectors.toList());
		
		
		return filteredTasks;
	}

	@Override
	public Task completeTask(Long taskId) throws Exception {
		
		Task task = getTaskById(taskId);
		task.setStatus(TaskStatus.DONE);
		
		return taskRepository.save(task);
	}

}
