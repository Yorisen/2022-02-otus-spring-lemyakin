package ru.otus.homework.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class TestQuestion {
    private final String question;
    private final String correctAnswer;
    private final List<String> answerOptions;
}
