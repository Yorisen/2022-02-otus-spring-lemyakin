package ru.otus.homework.repositories;

import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface GenreRepository {
    Genre insert(Genre genre);
    Genre findById(BigDecimal id);
    List<Genre> findAll();
    void deleteById(BigDecimal id);
}
