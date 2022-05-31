package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.service.BookService;
import ru.otus.homework.shell.viewer.BooksViewer;
import ru.otus.homework.shell.viewer.CommentsViewer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ShellComponent
@RequiredArgsConstructor
public class BookServiceCommands {
    private final BookService bookService;
    private final BooksViewer booksViewer;
    private final CommentsViewer commentsViewer;

    @ShellMethod(value = "Get all books", key = {"books", "ba"})
    public void getBooks() {
        List<Book> books = bookService.findAll();
        booksViewer.view(books);
    }

    @ShellMethod(value = "Get book by id", key = {"book", "b"})
    public void getBookById(@ShellOption({"-i", "--id"}) String id) {
        Optional<Book> book = bookService.findById(id);
        booksViewer.view(book);
    }

    @ShellMethod(value = "Insert new book", key = {"book_insert", "bi"})
    public void insertBook(@ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_name"}) String authorId,
                           @ShellOption({"-g", "--genre_name"}) String genreId) {
        bookService.insert(name, authorId, genreId);
    }

    @ShellMethod(value = "Delete book", key = {"book_delete", "bd"})
    public void deleteBook(@ShellOption({"-i", "--id"}) String id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Update book", key = {"book_update", "bu"})
    public void updateBook(@ShellOption({"-i", "--id"}) String id,
                           @ShellOption({"-n", "--name"}) String name,
                           @ShellOption({"-a", "--author_name"}) String authorId,
                           @ShellOption({"-g", "--genre_name"}) String genreId) {
        bookService.update(id, name, authorId, genreId);
    }

    @ShellMethod(value = "Add book comment", key = {"book_comment_insert", "bci"})
    public void addComment(@ShellOption({"-i", "--book_id"}) String id,
                           @ShellOption({"-n", "--nickname"}) String nickname,
                           @ShellOption({"-c", "--comment"}) String comment) {
        bookService.addComment(id, new Comment(getUuid(), nickname, comment, Instant.now()));
    }

    @ShellMethod(value = "Delete book comment", key = {"book_comment_delete", "bcd"})
    public void deleteComment(@ShellOption({"-bi", "--book_id"}) String bookId,
                              @ShellOption({"-ci", "--comment_id"}) String commentId) {
        bookService.deleteComment(bookId, commentId);
    }

    @ShellMethod(value = "Update book comment", key = {"book_comment_update", "bcu"})
    public void updateComment(@ShellOption({"-bi", "--book_id"}) String bookId,
                              @ShellOption({"-ci", "--comment_id"}) String commentId,
                              @ShellOption({"-n", "--nickname"}) String nickname,
                              @ShellOption({"-c", "--comment"}) String comment) {
        bookService.updateComment(bookId, commentId, nickname, comment);
    }

    @ShellMethod(value = "Get comments for book", key = {"book_comments", "bca"})
    public void getComments(@ShellOption({"-i", "--book_id"}) String id) {
        Optional<Book> book = bookService.findById(id);
        commentsViewer.view(book.isPresent() ? book.get().getComments() : new ArrayList<>());
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }
}
