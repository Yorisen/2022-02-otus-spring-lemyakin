package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.AuthorWithLinks;

import java.util.List;
import java.util.Optional;

public interface AuthorsViewer {
    void view(List<AuthorWithLinks> authors);
    void view(Optional<AuthorWithLinks> author);
}
