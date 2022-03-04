package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class TestQuestion {

    private final String question;
    private final String correctAnswer;
    private final List<String> answerOptions;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Question: ").append(question).append("\n")
                .append("Answer options:\n");

        for (int answerNumber = 0, numberOfAnswerOptions = answerOptions.size();
             numberOfAnswerOptions > answerNumber;
             answerNumber++) {

            stringBuilder.append(answerNumber).append(" - ").append(answerOptions.get(answerNumber)).append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestion that = (TestQuestion) o;
        return Objects.equals(question, that.question) &&
                Objects.equals(correctAnswer, that.correctAnswer) &&
                Objects.equals(answerOptions, that.answerOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, correctAnswer, answerOptions);
    }
}
