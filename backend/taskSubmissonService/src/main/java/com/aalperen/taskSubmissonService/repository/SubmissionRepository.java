package com.aalperen.taskSubmissonService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aalperen.taskSubmissonService.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{

	List<Submission> findByTaskId(Long taskId);
}
