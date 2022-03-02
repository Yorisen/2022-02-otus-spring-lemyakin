package ru.otus.homework.domain;

import java.util.ArrayList;
import java.util.List;

public final class TestQuestionBuilder {
    public static TestQuestion build() {
        String question = "Question";
        String correctAnswer = "Answer2";
        List<String> answerOptions = new ArrayList<>();
        answerOptions.add("Answer1");
        answerOptions.add("Answer2");
        answerOptions.add("Answer3");
        answerOptions.add("Answer4");
        return new TestQuestion(question, correctAnswer, answerOptions);
    }
}
