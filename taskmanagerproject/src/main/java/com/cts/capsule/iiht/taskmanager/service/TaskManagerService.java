package com.cts.capsule.iiht.taskmanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.capsule.iiht.taskmanager.entity.ParentTask;
import com.cts.capsule.iiht.taskmanager.entity.Task;
import com.cts.capsule.iiht.taskmanager.repository.ParentTaskRepository;
import com.cts.capsule.iiht.taskmanager.repository.TaskManagerRepository;

@Service
public class TaskManagerService {
	
	@Autowired
	TaskManagerRepository taskmanagerRepo;
	
	@Autowired
	ParentTaskRepository parentTaskRepo;
	
	/**
	 * 
	 * @return
	 */
	public List<Task> findAllTasks(){
		return taskmanagerRepo.findAll();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public List<ParentTask> findAllParentTasks() {
		return parentTaskRepo.findAll();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Task findTask(Integer id) {
		Optional<Task> task = taskmanagerRepo.findById(id);
		return task.isPresent() ? task.get() : null;
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ParentTask findParentTask(Integer id) {
		Optional<ParentTask> task = parentTaskRepo.findById(id);
		return task.isPresent() ? task.get() : null;
	}

	/**
	 * 
	 * @param task
	 */
	public void addTask(Task task) {
		setParentTask(task);
		taskmanagerRepo.save(task);
	}
	
	/**
	 * 
	 * @param task
	 */
	public void addParentTask(ParentTask task) {
		parentTaskRepo.save(task);
	}

	/**
	 * 
	 * @param task
	 */
	public void updateTask(Task task) {
		taskmanagerRepo.save(task);
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteTask(Integer id) {
		Optional<Task> taskOpt = taskmanagerRepo.findById(id);
		if (taskOpt.isPresent()) {
			Task task = taskOpt.get();
			// task.getParentTask().removeTask(task);
			task.setParentTask(null);
			taskmanagerRepo.deleteById(id);
		}
	}

	/**
	 * 
	 * @param id
	 */
	public void endTask(Integer id) {
		Optional<Task> taskOpt = taskmanagerRepo.findById(id);
		if (taskOpt.isPresent()) {
			Task task = taskOpt.get();
			task.setEndDate(new Date());
			taskmanagerRepo.save(task);
		}
	}
	


	/**
	 * 	
	 * @param task
	 */
	private void setParentTask(Task task) {
		if (task.getParentTask() != null) {
			Optional<ParentTask> pt = parentTaskRepo.findById(task.getParentTask().getId());
			if (pt.isPresent()) {
				task.setParentTask(pt.get());
			} else {
				task.setParentTask(null);
			}
		}
	}

	
	
	
	
	
}
