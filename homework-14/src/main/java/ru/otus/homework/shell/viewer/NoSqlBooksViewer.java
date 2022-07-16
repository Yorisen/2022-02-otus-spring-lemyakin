package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.nosql.Book;

import java.util.List;
import java.util.Optional;

public interface NoSqlBooksViewer {
    void view(List<Book> books);
 }
