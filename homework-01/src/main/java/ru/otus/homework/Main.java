package ru.otus.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.service.TestingService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("./spring-context.xml");
        TestingService testingService = classPathXmlApplicationContext.getBean(TestingService.class);
        List<TestQuestion> testQuestions = testingService.getTestQuestions();
        for (TestQuestion testQuestion: testQuestions) {
            System.out.println(testQuestion.toString());
        }
    }

}
