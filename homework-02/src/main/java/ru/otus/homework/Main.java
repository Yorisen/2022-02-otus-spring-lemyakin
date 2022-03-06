package ru.otus.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.service.TestingService;

import java.util.List;

@Configuration
@ComponentScan
@PropertySource("classpath:config.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        TestingService testingService = applicationContext.getBean(TestingService.class);
        testingService.run();
    }

}
