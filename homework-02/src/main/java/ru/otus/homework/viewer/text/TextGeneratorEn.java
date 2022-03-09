package ru.otus.homework.viewer.text;

import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Student;

import java.util.List;

@Component
public class TextGeneratorEn implements TextGenerator{
    private static final String INTRODUCE_YOURSELF = "Introduce yourself:";
    private static final String NAME_REQUEST = "What is your name?";
    private static final String SURNAME_REQUEST = "What is your surname?";
    private static final String TEST_START = "\nThe test has begun...";
    private static final String TEST_END = "\nThe test is over.\n";


    @Override
    public String getIntroduceYourself() {
        return INTRODUCE_YOURSELF;
    }

    @Override
    public String getNameRequest() {
        return NAME_REQUEST;
    }

    @Override
    public String getSurnameRequest() {
        return SURNAME_REQUEST;
    }

    @Override
    public String getTestStart() {
        return TEST_START;
    }

    @Override
    public String getTestEnd() {
        return TEST_END;
    }

    @Override
    public String getTestQuestion(String testQuestion, List<String> answerOptions,
                                  int numberOfAnswerOptions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nQuestion: ").append(testQuestion).append("\n")
                .append("Answer options:\n");

        for (int answerNumber = 0; numberOfAnswerOptions > answerNumber; answerNumber++) {
            stringBuilder.append(answerNumber).append(" - ").append(answerOptions.get(answerNumber)).append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public String getAnswerRequest(int numberOfAnswerOptions) {
        return "Enter value from 0 to " + (numberOfAnswerOptions - 1);
    }

    @Override
    public String getResults(Student student, int numberOfQuestions, int numberOfCorrectAnswers, boolean isTestPassed) {
        return student.getName() + " " + student.getSurname() +
                ", you correctly answered " + numberOfCorrectAnswers +
                " questions out of " + numberOfQuestions +
                ". You " + ((isTestPassed) ? "passed" : "did not pass") + " the test.";
    }

    @Override
    public String getInvalidPassingScore(int numberOfTestQuestions, int testPassingScore) {
        return "Test passing score(" + testPassingScore +
                ") is greater then number of test questions(" + numberOfTestQuestions + ").\n" +
                "Correct parameter 'test.passing-score' in 'config.properties' file.";
    }
}
