package ru.otus.homework.service;

import ru.otus.homework.domain.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(BigDecimal id);
    Book insert(String bookName, BigDecimal authorId, BigDecimal genreId);
    Book update(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId);
    void deleteById(BigDecimal id);
}
