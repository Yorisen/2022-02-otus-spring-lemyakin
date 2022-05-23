package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreViewer {
    void view(List<Genre> genres);
    void view(Optional<Genre> genre);
}
