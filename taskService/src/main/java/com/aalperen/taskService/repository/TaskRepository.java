package com.aalperen.taskService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.taskService.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	List<Task> findByAssignedUserId(Long userId);

}
