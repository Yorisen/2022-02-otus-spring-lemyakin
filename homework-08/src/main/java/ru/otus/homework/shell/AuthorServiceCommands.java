package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.AuthorWithLinks;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.shell.viewer.AuthorsViewer;

import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class AuthorServiceCommands {
    private final AuthorService authorService;
    private final AuthorsViewer authorsViewer;

    @ShellMethod(value = "Get all authors", key = {"authors", "aa"})
    public void getAuthors() {
        List<AuthorWithLinks> authors = authorService.findAll();
        authorsViewer.view(authors);
    }

    @ShellMethod(value = "Get author by id", key = {"author", "a"})
    public void getAuthorById(@ShellOption({"-i", "--id"}) String id) {
        Optional<AuthorWithLinks> author = authorService.findById(id);
        authorsViewer.view(author);
    }

    @ShellMethod(value = "Insert new author", key = {"author_insert", "ai"})
    public void insertAuthor(@ShellOption({"-n", "--name"}) String name) {
        authorService.insert(name);
    }

    @ShellMethod(value = "Update author", key = {"author_update", "au"})
    public void updateAuthor(@ShellOption({"-i", "--id"}) String id,
                             @ShellOption({"-n", "--name"}) String name) {
        authorService.update(id, name);
    }
}
