package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.Author;

import java.util.List;

public interface AuthorsViewer {
    void view(List<Author> authors);
    void view(Author author);
}
