package ru.otus.homework.service;

import ru.otus.homework.domain.nosql.Book;

import java.util.List;

public interface NoSqlBookService {
    List<Book> findAll();

}
