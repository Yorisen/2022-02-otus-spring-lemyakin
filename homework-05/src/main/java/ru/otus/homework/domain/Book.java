package ru.otus.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class Book {
    private final BigDecimal id;
    private final String name;
    private final Author author;
    private final Genre genre;
}
