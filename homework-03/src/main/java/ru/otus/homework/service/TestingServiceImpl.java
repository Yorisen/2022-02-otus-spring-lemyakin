package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.TestQuestionsDao;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.viewer.Viewer;

import java.util.List;

@SuppressWarnings("unused")
@Service
public class TestingServiceImpl implements TestingService {

    private final TestQuestionsDao testQuestionsDao;
    private final Viewer viewer;
    private final int testPassingScore;

    public TestingServiceImpl(TestQuestionsDao testQuestionsDao, Viewer viewer,
                              @Value("${test.passing-score}") int testPassingScore) {
        this.testQuestionsDao = testQuestionsDao;
        this.viewer = viewer;
        this.testPassingScore = testPassingScore;
    }

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
