package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsViewer {
    void view(List<Author> authors);
    void view(Optional<Author> author);
}
