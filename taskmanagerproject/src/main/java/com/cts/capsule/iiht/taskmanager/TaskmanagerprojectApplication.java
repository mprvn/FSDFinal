package com.cts.capsule.iiht.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cts.capsule.iiht.taskmanager")
public class TaskmanagerprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerprojectApplication.class, args);
	}

}
