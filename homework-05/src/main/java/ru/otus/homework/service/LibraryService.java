package ru.otus.homework.service;

import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.util.List;

public interface LibraryService {
    List<Genre> getGenres();
    List<Author> getAuthors();
    List<Book> getBooks();
    Genre getGenreById(BigDecimal id);
    Author getAuthorById(BigDecimal id);
    Book getBookById(BigDecimal id);
    void insertGenre(BigDecimal id, String name);
    void insertAuthor(BigDecimal id, String name, String surname, String patronymic);
    void insertBook(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId);
    void deleteGenreById(BigDecimal id);
    void deleteAuthorById(BigDecimal id);
    void deleteBookById(BigDecimal id);
    void updateBook(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId);
}
