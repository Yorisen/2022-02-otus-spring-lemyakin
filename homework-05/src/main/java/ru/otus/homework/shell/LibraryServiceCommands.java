package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.service.LibraryService;
import ru.otus.homework.shell.viewer.AuthorsViewerTableWithBorder;
import ru.otus.homework.shell.viewer.BooksViewerTableWithBorder;
import ru.otus.homework.shell.viewer.GenreViewerTableWithBorder;

import java.math.BigDecimal;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class LibraryServiceCommands {
    private final LibraryService libraryService;
    private final GenreViewerTableWithBorder genreViewerTableWithBorder;
    private final AuthorsViewerTableWithBorder authorsViewerTableWithBorder;
    private final BooksViewerTableWithBorder booksViewerTableWithBorder;

    @ShellMethod(value = "Get all genres", key = {"genres", "ga"})
    public void getGenres() {
        List<Genre> genres = libraryService.getGenres();
        genreViewerTableWithBorder.view(genres);
    }

    @ShellMethod(value = "Get all authors", key = {"authors", "aa"})
    public void getAuthors() {
        List<Author> authors = libraryService.getAuthors();
        authorsViewerTableWithBorder.view(authors);
    }

    @ShellMethod(value = "Get all books", key = {"books", "ba"})
    public void getBooks() {
        List<Book> books = libraryService.getBooks();
        booksViewerTableWithBorder.view(books);
    }

    @ShellMethod(value = "Get genre by id", key = {"genre", "g"})
    public void getGenreById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Genre genre = libraryService.getGenreById(id);
        genreViewerTableWithBorder.view(genre);
    }

    @ShellMethod(value = "Get author by id", key = {"author", "a"})
    public void getAuthorById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Author author = libraryService.getAuthorById(id);
        authorsViewerTableWithBorder.view(author);
    }

    @ShellMethod(value = "Get book by id", key = {"book", "b"})
    public void getBookById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Book book = libraryService.getBookById(id);
        booksViewerTableWithBorder.view(book);
    }

    @ShellMethod(value = "Insert new book", key = {"book_insert", "bi"})
    public void insertBook(@ShellOption({"-i", "--id"}) BigDecimal id,
                           @ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_id"}) BigDecimal authorId,
                           @ShellOption({"-g", "--genre_id"}) BigDecimal genreId) {
        libraryService.insertBook(id, name, authorId, genreId);
    }

    @ShellMethod(value = "Insert new author", key = {"author_insert", "ai"})
    public void insertAuthor(@ShellOption({"-i", "--id"}) BigDecimal id,
                           @ShellOption({"-n", "--name"}) String name,
                           @ShellOption(defaultValue = "", value = {"-s", "--surname"}) String surname,
                           @ShellOption(defaultValue = "", value = {"-p", "--patronymic"}) String patronymic) {
        libraryService.insertAuthor(id, name, surname, patronymic);
    }

    @ShellMethod(value = "Insert new genre", key = {"genre_insert", "gi"})
    public void insertGenre(@ShellOption({"-i", "--id"}) BigDecimal id,
                             @ShellOption({"-n", "--name"}) String name) {
        libraryService.insertGenre(id, name);
    }

    @ShellMethod(value = "Delete book", key = {"book_delete", "bd"})
    public void deleteBook(@ShellOption({"-i", "--id"}) BigDecimal id) {
        libraryService.deleteBookById(id);
    }

    @ShellMethod(value = "Delete author", key = {"author_delete", "ad"})
    public void insertAuthor(@ShellOption({"-i", "--id"}) BigDecimal id) {
        libraryService.deleteAuthorById(id);
    }

    @ShellMethod(value = "Delete genre", key = {"genre_delete", "gd"})
    public void insertGenre(@ShellOption({"-i", "--id"}) BigDecimal id) {
        libraryService.deleteGenreById(id);
    }

    @ShellMethod(value = "Update book", key = {"book_update", "bu"})
    public void updateBook(@ShellOption({"-i", "--id"}) BigDecimal id,
                           @ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_id"}) BigDecimal authorId,
                           @ShellOption({"-g", "--genre_id"}) BigDecimal genreId) {
        libraryService.updateBook(id, name, authorId, genreId);
    }
}
