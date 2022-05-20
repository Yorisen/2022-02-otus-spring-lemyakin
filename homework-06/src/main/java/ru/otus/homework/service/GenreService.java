package ru.otus.homework.service;

import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    Genre getById(BigDecimal id);
    Genre insert(String name);
    void deleteById(BigDecimal id);
}
