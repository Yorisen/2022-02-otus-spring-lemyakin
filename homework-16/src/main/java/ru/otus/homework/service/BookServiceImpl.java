package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.repositories.GenreRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    public Optional<Book> findById(BigDecimal id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId) {
        Book book;

        if (bookId != null) {
            book = update(bookId, bookName, authorId, genreId);
        } else {
            book = insert(bookName, authorId, genreId);
        }

        return book;
    }

    @Override
    @Transactional
    public Book insert(String bookName, BigDecimal authorId, BigDecimal genreId) {
        Book insertedBook = null;

        Book bookForInsert = new Book();
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Genre> genre = genreRepository.findById(genreId);

        if (author.isPresent()&& genre.isPresent()) {
            bookForInsert.setName(bookName);
            bookForInsert.setGenre(genre.get());
            bookForInsert.setAuthor(author.get());

            insertedBook = bookRepository.save(bookForInsert);
        }

        return insertedBook;
    }

    @Override
    @Transactional
    public Book update(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId) {
        Book updatedBook = null;

        Optional<Book> bookForUpdateOptional = bookRepository.findById(bookId);

        if (bookForUpdateOptional.isPresent()) {
            Optional<Author> author = authorRepository.findById(authorId);
            Optional<Genre> genre = genreRepository.findById(genreId);

            if (author.isPresent()&& genre.isPresent()) {
                Book bookForUpdate = bookForUpdateOptional.get();
                bookForUpdate.setName(bookName);
                bookForUpdate.setGenre(genre.get());
                bookForUpdate.setAuthor(author.get());

                updatedBook = bookRepository.save(bookForUpdate);
            }
        }

        return updatedBook;
    }

    @Override
    @Transactional
    public void deleteById(BigDecimal id) {
        bookRepository.deleteById(id);
    }
}
