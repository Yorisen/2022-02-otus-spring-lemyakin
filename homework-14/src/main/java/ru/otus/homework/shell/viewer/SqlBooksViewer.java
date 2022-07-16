package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.sql.Book;

import java.util.List;
import java.util.Optional;

public interface SqlBooksViewer {
    void view(List<Book> books);
 }
