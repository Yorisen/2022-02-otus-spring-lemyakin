package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.Genre;

import java.util.List;

public interface GenreViewer {
    void view(List<Genre> genres);
    void view(Genre genre);
}
