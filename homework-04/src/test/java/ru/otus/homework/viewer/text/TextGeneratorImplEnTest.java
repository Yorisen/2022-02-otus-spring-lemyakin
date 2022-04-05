package ru.otus.homework.viewer.text;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.domain.TestQuestionBuilder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TextGeneratorImplEnTest {
    @ComponentScan({"ru.otus.homework.viewer.text"})
    @Configuration
    static class TextGeneratorImplEnTestConfiguration {
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setDefaultEncoding("UTF-8");
            source.setBasenames("i18n/messages");
            return source;
        }

        @Bean
        public Student student() {
            return new Student("Ivan", "Ivanov");
        }

        @Bean
        public TestQuestion testQuestion() {
            return TestQuestionBuilder.build();
        }
    }

    @Autowired
    TextGenerator textGenerator;

    @Autowired
    Student student;

    @Autowired
    TestQuestion testQuestion;

    @Test
    void getTestQuestion() {
        String expected = """

                Question: Question
                Answer options:
                0 - Answer1
                1 - Answer2
                2 - Answer3
                3 - Answer4
                """;

        int numberOfAnswerOptions = testQuestion.getAnswerOptions().size();
        String result = textGenerator.getTestQuestion(testQuestion.getQuestion(), testQuestion.getAnswerOptions(),
                numberOfAnswerOptions);

        assertEquals(expected, result, "Strings should be equal");
    }

    @Test
    void getAnswerRequest() {
        String expected = "Enter value from 0 to 3";
        int numberOfAnswerOptions = 4;
        String result = textGenerator.getAnswerRequest(numberOfAnswerOptions);
        assertEquals(expected, result, "Strings should be equal");
    }

    @Test
    void getResultsTestIsPassed() {
        String expected = "Ivan Ivanov, you correctly answered 4 questions out of 5. You passed the test.";
        String result = textGenerator.getResults(student, 5,4, true);
        assertEquals(expected, result, "Strings should be equal");
    }

    @Test
    void getResultsTestDidNotPass() {
        String expected = "Ivan Ivanov, you correctly answered 3 questions out of 5. You did not pass the test.";
        String result = textGenerator.getResults(student, 5,3, false);
        assertEquals(expected, result, "Strings should be equal");
    }

    @Test
    void getInvalidPassingScore() {
        String expected = "Test passing score(10) is greater then number of test questions(5).\n" +
                "Correct parameter 'test.passing-score' in 'config.properties' file.";
        String result = textGenerator.getInvalidPassingScore(5, 10);
        assertEquals(expected, result, "Strings should be equal");
    }
}