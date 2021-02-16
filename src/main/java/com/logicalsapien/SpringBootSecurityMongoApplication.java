package com.logicalsapien;

import com.logicalsapien.startup.StartUpTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootSecurityMongoApplication {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringBootSecurityMongoApplication.class, args);

		context.getBean(StartUpTasks.class).populateDBWithRoles();
		context.getBean(StartUpTasks.class).checkDBUserListEmpty();
	}

}
