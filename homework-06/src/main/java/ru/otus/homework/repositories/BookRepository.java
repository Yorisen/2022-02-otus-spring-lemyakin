package ru.otus.homework.repositories;

import ru.otus.homework.domain.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository {
    Book insert(Book book);
    Book findById(BigDecimal id);
    List<Book> findAll();
    void deleteById(BigDecimal id);
    Book update(Book book);
}
