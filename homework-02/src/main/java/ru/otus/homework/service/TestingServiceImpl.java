package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.TestQuestionsDao;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.viewer.Viewer;

import java.util.List;

@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final TestQuestionsDao testQuestionsDao;
    private final Viewer viewer;
    private final int testPassingScore;

    @Override
    public void run() {
        List<TestQuestion> testQuestions = testQuestionsDao.get();
        int numberOfTestQuestions = testQuestions.size();

        if (numberOfTestQuestions >= testPassingScore) {
            Student student = viewer.showIntroduceYourself();
            viewer.showTheTestHasBegun();

            int numberOfCorrectAnswers = 0;
            for (TestQuestion testQuestion: testQuestions) {
                String response = viewer.showTestQuestion(testQuestion);
                if (response.equals(testQuestion.getCorrectAnswer())) {
                    numberOfCorrectAnswers++;
                }
            }
            viewer.showTheTestIsOver();

            boolean isTestPassed = numberOfCorrectAnswers >= testPassingScore;
            viewer.showResults(student, numberOfTestQuestions, numberOfCorrectAnswers,  isTestPassed);
        } else {
            viewer.showInvalidPassingScore(numberOfTestQuestions, testPassingScore);
        }
    }
}
