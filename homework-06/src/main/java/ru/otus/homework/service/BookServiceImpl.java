package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.repositories.GenreRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(BigDecimal id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book insert(String bookName, BigDecimal authorId, BigDecimal genreId) {
        Book insertedBook = null;

        Book bookForInsert = new Book();
        Author author = authorRepository.findById(authorId);
        Genre genre = genreRepository.findById(genreId);

        if (author != null && genre != null) {
            bookForInsert.setName(bookName);
            bookForInsert.setGenre(genre);
            bookForInsert.setAuthor(author);

            insertedBook = bookRepository.insert(bookForInsert);
        }

        return insertedBook;
    }

    @Override
    public Book update(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId) {
        Book updatedBook = null;

        Book bookForUpdate = bookRepository.findById(bookId);

        if (bookForUpdate != null) {
            Author author = authorRepository.findById(authorId);
            Genre genre = genreRepository.findById(genreId);

            if (author != null && genre != null) {
                bookForUpdate.setName(bookName);
                bookForUpdate.setGenre(genre);
                bookForUpdate.setAuthor(author);

                updatedBook = bookRepository.update(bookForUpdate);
            }
        }

        return updatedBook;
    }

    @Override
    public void deleteById(BigDecimal id) {
        bookRepository.deleteById(id);
    }
}
