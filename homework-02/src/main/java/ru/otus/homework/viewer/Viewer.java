package ru.otus.homework.viewer;

import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.TestQuestion;

public interface Viewer {
    Student showIntroduceYourself();
    void showTheTestHasBegun();
    void showTheTestIsOver();
    String showTestQuestion(TestQuestion testQuestion);
    void showResults(Student student, int numberOfQuestions, int numberOfCorrectAnswers, boolean isTestPassed);
    void showInvalidPassingScore(int numberOfTestQuestions, int testPassingScore);
}
