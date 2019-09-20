package com.cts.capsule.iiht.taskmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.capsule.iiht.taskmanager.entity.ParentTask;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer>{
	
}
