
package com.cts.capsule.iiht.taskmanager.service;

import static java.sql.Date.valueOf;
import static java.time.LocalDate.now;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.capsule.iiht.taskmanager.entity.ParentTask;
import com.cts.capsule.iiht.taskmanager.entity.Task;
import com.cts.capsule.iiht.taskmanager.repository.ParentTaskRepository;
import com.cts.capsule.iiht.taskmanager.repository.TaskManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskManagerServiceTest {


	@Autowired
    private TaskManagerService taskService;
	@Mock
	private TaskManagerRepository taskRepo;
	@Mock
	private ParentTaskRepository parentRepo;
	
	
	
	@Test
    public void findAllTasks() {
		addTask();
    	List<Task> tasks = taskService.findAllTasks();
    	assertNotNull(tasks);
    	
    }
	
	@Test
    public void findAllParentTasks() {
		addParentTask();
    	List<ParentTask> tasks = taskService.findAllParentTasks();
    	assertNotNull(tasks);
    	assertThat(tasks, hasSize(6));
    }

    @Test
    public void findById() {
    	assertNotNull(taskService.findTask(1));
    }

    @Test
    public void updateTask() {
    	final Task task = taskService.findTask(1);
    	task.setEndDate(new Date());
    	taskService.updateTask(task);
    }
    
    @Test
    public void completeTask() {
    	final Task task = taskService.findTask(1);
    	task.setEndDate(new Date());
    	task.setStatus(true);
    	taskService.updateTask(task);
    }

    
    public void addTask() {
        final Task task = new Task();
        task.setTask("Task1");
        task.setPriority(1);
        task.setStartDate(new Date());
        task.setEndDate(new Date());
        task.setParentTask(parentRepo.getOne(1));
        taskService.addTask(task);
    }
    
    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void addTaskConstraintViolation() {
        final Task task = new Task();
        task.setTask("Task1");
        task.setStartDate(new Date());
        task.setParentTask(parentRepo.getOne(1));
        taskService.addTask(task);
    }
      
    @Test
    public void addParentTask() {
        final ParentTask task = new ParentTask();
        task.setTask("Parent Task1");
        taskService.addParentTask(task);
    }


    @Test
    public void deleteTask() {
    	final Task task = new Task();
        task.setTask("Test Task");
        task.setStartDate(new Date());
        task.setParentTask(parentRepo.getOne(2));
    	Optional<Task>  tasks = Optional.of(task);
    	when(taskRepo.findById(2)).thenReturn(tasks);
    	taskService.deleteTask(2);
    }
    
    @Test
    public void endTask() {
    	final Task task = new Task();
        task.setTask("Test Task");
        task.setId(1);
        task.setStartDate(new Date());
		when(taskRepo.findById(1)).thenReturn(Optional.of(task));
		
    	taskService.endTask(1);
    }
    
    @Test
    public void endTaskFail() {
		when(taskRepo.findById(8)).thenReturn(Optional.empty());
    	taskService.endTask(8);
    }
    
    
	@Test
    public void findNonExistTask() {
		when(taskRepo.findById(1)).thenReturn(Optional.empty());
    	assertNull(taskService.findTask(8));
    }
	
	@Test
    public void deleteNonExistTask() {
		when(taskRepo.findById(8)).thenReturn(Optional.empty());
    	taskService.deleteTask(8);
    }
	
	@Test
    public void endExistingTask() {
		final Task task = new Task();
        task.setTask("Test Task");
        task.setStartDate(new Date());
		when(taskRepo.findById(1)).thenReturn(Optional.of(task));
		
    	taskService.endTask(1);
    }
	
	@Test
    public void findByParentTaskTask() {
		final Task task = new Task();
        task.setTask("Task1");
        task.setStartDate(new Date());
        ParentTask parentTask = new ParentTask();
        parentTask.setTask("parent Task1");
        parentTask.setId(1);
        task.setParentTask(parentTask);
		when(taskRepo.findByParentTaskTask("parent Task1")).thenReturn(Arrays.asList(task));
    	List<Task> tasks = taskRepo.findByParentTaskTask("parent Task1");
    	assertNotNull(tasks);
    	String input = task.toString();
    	String output = tasks.get(0).toString();
    	String inputParent = parentTask.toString();
    	String outputParent   = tasks.get(0).getParentTask().toString();
    	assertEquals(input, output);
    	assertEquals(inputParent, outputParent);
    	assertThat(tasks, hasSize(1));
    }
	
	@Test
	public void findParentTaskTest() {
		ParentTask task = new ParentTask();
		task.setId(1);
		task.setTask("partnet task");
		when(parentRepo.findById(1)).thenReturn(Optional.of(task));
		taskService.findParentTask(1);
	}
	
	@Test
	public void findParentTaskFailTest() {
		ParentTask task = new ParentTask();
		task.setId(1);
		task.setTask("partnet task");
		when(parentRepo.findById(1)).thenReturn(Optional.empty());
		taskService.findParentTask(1);
	}
	
	@Test
    public void addTaskWithParent() {
		Task task = new Task(1, "Task 1", 
	             valueOf(now()), true, valueOf(now().plusDays(10)), 
	             5, new ParentTask(1, "Parent Task 1"));
		when(taskRepo.findById(task.getParentTask().getId())).thenReturn(Optional.of(task));
		when(parentRepo.findById(task.getParentTask().getId())).thenReturn(Optional.empty());
		
		taskService.addTask(task);
    }
	
	@Test
    public void addTaskWithFailParent() {
		Task task = new Task(1, "Task 1", 
	             valueOf(now()), true, valueOf(now().plusDays(10)), 
	             5, new ParentTask(0, "Parent Task 1"));
		when(taskRepo.findById(task.getParentTask().getId())).thenReturn(Optional.of(task));
		when(parentRepo.findById(0)).thenReturn(Optional.empty());
		
		taskService.addTask(task);
    }
	
	
  
}
