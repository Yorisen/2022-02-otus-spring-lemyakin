package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.shell.viewer.*;

import java.math.BigDecimal;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreServiceCommands {
    private final GenreService genreService;
    private final GenreViewer genreViewer;

    @Transactional(readOnly=true)
    @ShellMethod(value = "Get all genres", key = {"genres", "ga"})
    public void getGenres() {
        List<Genre> genres = genreService.getAll();
        genreViewer.view(genres);
    }

    @Transactional(readOnly=true)
    @ShellMethod(value = "Get genre by id", key = {"genre", "g"})
    public void getGenreById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Genre genre = genreService.getById(id);
        genreViewer.view(genre);
    }

    @Transactional
    @ShellMethod(value = "Insert new genre", key = {"genre_insert", "gi"})
    public void insertGenre(@ShellOption({"-n", "--name"}) String name) {
        genreService.insert(name);
    }

    @Transactional
    @ShellMethod(value = "Delete genre", key = {"genre_delete", "gd"})
    public void deleteGenre(@ShellOption({"-i", "--id"}) BigDecimal id) {
        genreService.deleteById(id);
    }

}
