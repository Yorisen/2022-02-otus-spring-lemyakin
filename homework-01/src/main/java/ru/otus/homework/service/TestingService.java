package ru.otus.homework.service;

import ru.otus.homework.domain.TestQuestion;

import java.util.List;

public interface TestingService {

    List<TestQuestion> getTestQuestions();
}
