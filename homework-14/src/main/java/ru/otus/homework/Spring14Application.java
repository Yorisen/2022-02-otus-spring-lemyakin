package ru.otus.homework;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class Spring14Application {

	public static void main(String[] args) {
		SpringApplication.run(Spring14Application.class, args);

		/*try {
			Console.main(args);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}*/
	}

}
