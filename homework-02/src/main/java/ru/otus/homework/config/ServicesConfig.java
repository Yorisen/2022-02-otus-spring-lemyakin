package ru.otus.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.TestQuestionsDao;
import ru.otus.homework.service.TestingService;
import ru.otus.homework.service.TestingServiceImpl;
import ru.otus.homework.viewer.Viewer;

@Configuration
public class ServicesConfig {

    @Bean
    public TestingService testingService(TestQuestionsDao testQuestionsDao, Viewer viewer,
                                         @Value("${test.passing-score}") int testPassingScore) {
        return new TestingServiceImpl(testQuestionsDao, viewer, testPassingScore);
    }
}
