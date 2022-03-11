package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.homework.service.TestingService;

@SpringBootApplication
public class Spring03Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Spring03Application.class, args);
		TestingService testingService = applicationContext.getBean(TestingService.class);
		testingService.run();
	}

}
