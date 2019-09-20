package com.cts.capsule.iiht.taskmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.capsule.iiht.taskmanager.controller.TaskManagerController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskmanagerprojectApplicationTests {
	
	@Autowired
	private TaskManagerController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	
}
