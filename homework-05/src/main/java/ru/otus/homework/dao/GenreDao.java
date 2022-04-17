package ru.otus.homework.dao;

import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface GenreDao {

    void insert(BigDecimal id, String name);

    Genre getById(BigDecimal id);

    List<Genre> getAll();

    void deleteById(BigDecimal id);
}
