package ru.otus.homework;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class Spring08Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring08Application.class, args);
	}

}
