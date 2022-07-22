package ru.otus.homework.service;

import ru.otus.homework.domain.Author;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(BigDecimal id);
    Author insert(String name, String surname, String patronymic);
    void deleteById(BigDecimal id);
}
