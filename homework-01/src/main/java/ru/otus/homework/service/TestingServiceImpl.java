package ru.otus.homework.service;

import ru.otus.homework.dao.TestQuestionsDao;
import ru.otus.homework.domain.TestQuestion;

import java.util.List;

public class TestingServiceImpl implements TestingService {

    private final TestQuestionsDao testQuestionsDao;

    public TestingServiceImpl(TestQuestionsDao testQuestionsDao) {
        this.testQuestionsDao = testQuestionsDao;
    }

    public List<TestQuestion> getTestQuestions() {
        return testQuestionsDao.get();
    }
}
