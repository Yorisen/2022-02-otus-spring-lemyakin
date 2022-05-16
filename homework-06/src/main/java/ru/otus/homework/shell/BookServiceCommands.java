package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Book;
import ru.otus.homework.service.BookService;
import ru.otus.homework.shell.viewer.BookWithCommentsViewer;
import ru.otus.homework.shell.viewer.BooksViewer;

import java.math.BigDecimal;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookServiceCommands {
    private final BookService bookService;
    private final BooksViewer booksViewer;
    private final BookWithCommentsViewer bookWithCommentsViewer;

    @Transactional(readOnly=true)
    @ShellMethod(value = "Get all books", key = {"books", "ba"})
    public void getBooks() {
        List<Book> books = bookService.findAll();
        booksViewer.view(books);
    }

    @Transactional(readOnly=true)
    @ShellMethod(value = "Get book by id", key = {"book", "b"})
    public void getBookById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Book book = bookService.findById(id);
        booksViewer.view(book);
    }

    @Transactional(readOnly=true)
    @ShellMethod(value = "Get book with comments by id", key = {"book_with_comments", "bwc"})
    public void getBookWithCommentsById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Book book = bookService.findById(id);
        bookWithCommentsViewer.view(book);
    }

    @Transactional
    @ShellMethod(value = "Insert new book", key = {"book_insert", "bi"})
    public void insertBook(@ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_id"}) BigDecimal authorId,
                           @ShellOption({"-g", "--genre_id"}) BigDecimal genreId) {
        bookService.insert(name, authorId, genreId);
    }

    @Transactional
    @ShellMethod(value = "Delete book", key = {"book_delete", "bd"})
    public void deleteBook(@ShellOption({"-i", "--id"}) BigDecimal id) {
        bookService.deleteById(id);
    }

    @Transactional
    @ShellMethod(value = "Update book", key = {"book_update", "bu"})
    public void updateBook(@ShellOption({"-i", "--id"}) BigDecimal id,
                           @ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_id"}) BigDecimal authorId,
                           @ShellOption({"-g", "--genre_id"}) BigDecimal genreId) {
        bookService.update(id, name, authorId, genreId);
    }
}
