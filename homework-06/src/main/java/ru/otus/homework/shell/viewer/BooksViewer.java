package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.Book;

import java.util.List;

public interface BooksViewer {
    void view(List<Book> books);
    void view(Book book);
}
