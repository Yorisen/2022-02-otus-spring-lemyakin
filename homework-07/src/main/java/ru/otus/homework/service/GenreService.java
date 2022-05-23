package ru.otus.homework.service;

import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> getAll();
    Optional<Genre> getById(BigDecimal id);
    Genre insert(String name);
    void deleteById(BigDecimal id);
}
