package ru.otus.homework.viewer.text;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.domain.TestQuestionBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextGeneratorImplEnTest {
    private final TextGenerator textGenerator;
    private final List<String> answerOptions;
    private final String testQuestionText;
    private final Student student;

    TextGeneratorImplEnTest() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("i18n/messages");
        textGenerator = new TextGeneratorImpl(messageSource, "");
        TestQuestion testQuestion = TestQuestionBuilder.build();
        testQuestionText = testQuestion.getQuestion();
        answerOptions = testQuestion.getAnswerOptions();
        student = new Student("Ivan", "Ivanov");
    }

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

        int numberOfAnswerOptions = answerOptions.size();
        String result = textGenerator.getTestQuestion(testQuestionText, answerOptions,
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