package ru.otus.homework.service;

import ru.otus.homework.domain.sql.Book;

public interface BookConverterService {
    ru.otus.homework.domain.nosql.Book convert(Book sqlBook);
}
