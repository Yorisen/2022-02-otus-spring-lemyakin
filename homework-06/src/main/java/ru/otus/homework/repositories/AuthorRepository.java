package ru.otus.homework.repositories;

import ru.otus.homework.domain.Author;

import java.math.BigDecimal;
import java.util.List;

public interface AuthorRepository {
    Author insert(Author author);
    Author findById(BigDecimal id);
    List<Author> findAll();
    void deleteById(BigDecimal id);
}
