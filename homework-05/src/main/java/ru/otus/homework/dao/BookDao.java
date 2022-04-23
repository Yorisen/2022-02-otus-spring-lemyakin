package ru.otus.homework.dao;

import ru.otus.homework.domain.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookDao {

    void insert(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId);

    Book getById(BigDecimal id);

    List<Book> getAll();

    void deleteById(BigDecimal id);

    void update(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId);
}
