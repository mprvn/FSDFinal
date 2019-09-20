package com.cts.capsule.iiht.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.capsule.iiht.taskmanager.entity.ParentTask;
import com.cts.capsule.iiht.taskmanager.entity.Task;
import com.cts.capsule.iiht.taskmanager.service.TaskManagerService;

@CrossOrigin
@RestController

public class TaskManagerController {
	
	@Autowired
	private TaskManagerService service;

	@RequestMapping(path="tasks", method = RequestMethod.GET)
	public List<Task> fetchAllTasks() {
		return service.findAllTasks();
	}
	
	@RequestMapping(path="tasks/{id}", method = RequestMethod.GET)
	public Task fetchTask(@PathVariable Integer id) {
		return service.findTask(id);
	}	
	
	@RequestMapping(path="/ptasks", method=RequestMethod.GET)
	public List<ParentTask> findAllParentTasks(){
		return service.findAllParentTasks();
	}
	
		
	@RequestMapping(path="/tasks", method=RequestMethod.POST)
	public void addTask(@RequestBody Task task) {
		 service.addTask(task);
	}
	
	@RequestMapping(path="/ptasks", method=RequestMethod.POST)
	public void addParentTask(@RequestBody ParentTask task) {
		 service.addParentTask(task);
	}
	
	@RequestMapping(path="/tasks", method=RequestMethod.PUT)
	public void updateTask(@RequestBody Task task){
		 service.updateTask(task);
	}
	
	@RequestMapping(path="/tasks/{id}", method=RequestMethod.DELETE)
	public void deleteTask(@PathVariable Integer id){
		 service.deleteTask(id);
	}
	
	@RequestMapping(path="/tasks/{id}", method=RequestMethod.PUT)
	public void endTask(@PathVariable Integer id){
		 service.endTask(id);
	}
	
}
