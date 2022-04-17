package ru.otus.homework.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class Genre {
    private final BigDecimal id;
    private final String name;
}
