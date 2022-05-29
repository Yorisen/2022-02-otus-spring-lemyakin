package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Book;
import ru.otus.homework.service.BookService;
import ru.otus.homework.shell.viewer.BooksViewer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class BookServiceCommands {
    private final BookService bookService;
    private final BooksViewer booksViewer;

    @ShellMethod(value = "Get all books", key = {"books", "ba"})
    public void getBooks() {
        List<Book> books = bookService.findAll();
        booksViewer.view(books);
    }

    @ShellMethod(value = "Get book by id", key = {"book", "b"})
    public void getBookById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Optional<Book> book = bookService.findById(id);
        booksViewer.view(book);
    }

    @ShellMethod(value = "Insert new book", key = {"book_insert", "bi"})
    public void insertBook(@ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_id"}) BigDecimal authorId,
                           @ShellOption({"-g", "--genre_id"}) BigDecimal genreId) {
        bookService.insert(name, authorId, genreId);
    }

    @ShellMethod(value = "Delete book", key = {"book_delete", "bd"})
    public void deleteBook(@ShellOption({"-i", "--id"}) BigDecimal id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Update book", key = {"book_update", "bu"})
    public void updateBook(@ShellOption({"-i", "--id"}) BigDecimal id,
                           @ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_id"}) BigDecimal authorId,
                           @ShellOption({"-g", "--genre_id"}) BigDecimal genreId) {
        bookService.update(id, name, authorId, genreId);
    }
}
