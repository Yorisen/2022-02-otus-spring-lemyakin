package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class TestQuestion {
    private final String question;
    private final String correctAnswer;
    private final List<String> answerOptions;
}
