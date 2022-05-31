package ru.otus.homework.service;

import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(String id);
    Book insert(String bookName, String authorName, String genreName);
    Book update(String bookId, String bookName, String authorName, String genreName);
    void deleteById(String id);
    Book addComment(String id, Comment comment);
    Book deleteComment(String bookId, String commentId);
    Book updateComment(String bookId, String commentId, String nickname, String comment);
}
