package ru.otus.homework.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.domain.TestQuestionBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestQuestionsDaoCSVTest {

    @ComponentScan({"ru.otus.homework.reader",
            "ru.otus.homework.dao"})
    @Configuration
    static class TestQuestionsDaoCSVTestConfiguration {
        @Bean
        public MessageSource testQuestionsMessageSource() {
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setDefaultEncoding("UTF-8");
            source.setBasenames("i18n/messages");
            return source;
        }
    }

    @Autowired
    TestQuestionsDaoCSV testQuestionsDao;

    @Test
    void getTestQuestions() {
        List<TestQuestion> expectedTestQuestions = new ArrayList<>();
        TestQuestion expectedTestQuestion = TestQuestionBuilder.build();
        expectedTestQuestions.add(expectedTestQuestion);

        List<TestQuestion> testQuestions = testQuestionsDao.get();

        assertIterableEquals(expectedTestQuestions, testQuestions, "Arrays not equal");
    }
}