package ru.otus.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class Author {
    private final BigDecimal id;
    private final String name;
    private final String surname;
    private final String patronymic;
}
