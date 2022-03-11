package ru.otus.homework.viewer.text;

import ru.otus.homework.domain.Student;

import java.util.List;

public interface TextGenerator {
    String getIntroduceYourself();
    String getNameRequest();
    String getSurnameRequest();
    String getTestStart();
    String getTestEnd();
    String getTestQuestion(String testQuestion, List<String> answerOptions,
                           int numberOfAnswerOptions);
    String getAnswerRequest(int numberOfAnswerOptions);
    String getResults(Student student, int numberOfQuestions, int numberOfCorrectAnswers,
                      boolean isTestPassed);

    String getInvalidPassingScore(int numberOfTestQuestions, int testPassingScore);
}
