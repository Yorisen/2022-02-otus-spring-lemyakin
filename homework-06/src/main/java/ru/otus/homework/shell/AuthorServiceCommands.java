package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Author;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.shell.viewer.AuthorsViewer;

import java.math.BigDecimal;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorServiceCommands {
    private final AuthorService authorService;
    private final AuthorsViewer authorsViewer;

    @ShellMethod(value = "Get all authors", key = {"authors", "aa"})
    public void getAuthors() {
        List<Author> authors = authorService.findAll();
        authorsViewer.view(authors);
    }

    @ShellMethod(value = "Get author by id", key = {"author", "a"})
    public void getAuthorById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Author author = authorService.findById(id);
        authorsViewer.view(author);
    }

    @ShellMethod(value = "Insert new author", key = {"author_insert", "ai"})
    public void insertAuthor(@ShellOption({"-n", "--name"}) String name,
                             @ShellOption(defaultValue = "", value = {"-s", "--surname"}) String surname,
                             @ShellOption(defaultValue = "", value = {"-p", "--patronymic"}) String patronymic) {
        authorService.insert(name, surname, patronymic);
    }

    @ShellMethod(value = "Delete author", key = {"author_delete", "ad"})
    public void deleteAuthor(@ShellOption({"-i", "--id"}) BigDecimal id) {
        authorService.deleteById(id);
    }
}
