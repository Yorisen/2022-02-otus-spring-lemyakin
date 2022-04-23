package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    @Override
    public List<Genre> getGenres() {
        try {
            return genreDao.getAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Author> getAuthors() {
        try {
            return authorDao.getAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Book> getBooks() {
        try {
            return bookDao.getAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Genre getGenreById(BigDecimal id) {
        try {
            return genreDao.getById(id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Author getAuthorById(BigDecimal id) {
        try {
            return authorDao.getById(id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Book getBookById(BigDecimal id) {
        try {
            return bookDao.getById(id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void insertGenre(BigDecimal id, String name) {
        genreDao.insert(id, name);
    }

    @Override
    public void insertAuthor(BigDecimal id, String name, String surname, String patronymic) {
        authorDao.insert(id, name, surname, patronymic);
    }

    @Override
    public void insertBook(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId) {
        bookDao.insert(bookId, bookName, authorId, genreId);
    }

    @Override
    public void deleteGenreById(BigDecimal id) {
        genreDao.deleteById(id);
    }

    @Override
    public void deleteAuthorById(BigDecimal id) {
        authorDao.deleteById(id);
    }

    @Override
    public void deleteBookById(BigDecimal id) {
        bookDao.deleteById(id);
    }

    @Override
    public void updateBook(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId) {
        bookDao.update(bookId, bookName, authorId, genreId);
    }


}
