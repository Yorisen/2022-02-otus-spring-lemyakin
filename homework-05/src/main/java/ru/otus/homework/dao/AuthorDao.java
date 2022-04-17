package ru.otus.homework.dao;

import ru.otus.homework.domain.Author;

import java.math.BigDecimal;
import java.util.List;

public interface AuthorDao {

    void insert(BigDecimal id, String name, String surname, String patronymic);

    Author getById(BigDecimal id);

    List<Author> getAll();

    void deleteById(BigDecimal id);
}
