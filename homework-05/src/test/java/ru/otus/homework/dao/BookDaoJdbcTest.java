package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Builder;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    BookDaoJdbc bookDaoJdbc;

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final BigDecimal NOT_EXISTING_BOOK_ID = BigDecimal.valueOf(1000);

    @DisplayName("добавлять книгу")
    @Test
    void shouldInsertBook() {
        Book existingBook = Builder.buildExistingBook();
        Book expectedBook = new Book(BigDecimal.valueOf(EXPECTED_BOOKS_COUNT + 1), "Book3",
                existingBook.getAuthor(), existingBook.getGenre());

        bookDaoJdbc.insert(expectedBook.getId(), expectedBook.getName(),
                expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());

        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("получать ошибку при попытке добавить книгу с уже существующим id")
    @Test
    void shouldGetExceptionOnInsertBookWithExistingId() {
        Book existingBook = Builder.buildExistingBook();
        assertThatCode(() -> bookDaoJdbc.insert(existingBook.getId(), existingBook.getName(),
                existingBook.getAuthor().getId(), existingBook.getGenre().getId()))
                .isInstanceOf(DuplicateKeyException.class);
    }

    @DisplayName("получать книгу по существующему id")
    @Test
    void shouldGetBookByExistingId() {
        Book expectedBook = Builder.buildExistingBook();
        Book actualBook = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("получать ошибку при попытке получить книгу с не существующим id")
    @Test
    void shouldGetExceptionOnGettingBookWithNotExistingId() {
        assertThatCode(() -> bookDaoJdbc.getById(NOT_EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("получать все книги")
    @Test
    void shouldGetAllBooks() {
        Book expectedBook = Builder.buildExistingBook();
        List<Book> actualBooks = bookDaoJdbc.getAll();
        assertThat(actualBooks.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
        assertThat(actualBooks).containsAnyOf(expectedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        Book existingBook = Builder.buildExistingBook();
        assertThatCode(() -> bookDaoJdbc.getById(existingBook.getId())).doesNotThrowAnyException();
        bookDaoJdbc.deleteById(existingBook.getId());
        assertThatCode(() -> bookDaoJdbc.getById(existingBook.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldUpdateBook() {
        Book bookForUpdate = Builder.buildExistingBook();
        Book updatedBook = new Book(bookForUpdate.getId(), "UpdatedBookName",
                bookForUpdate.getAuthor(), bookForUpdate.getGenre());
        bookDaoJdbc.update(updatedBook.getId(), updatedBook.getName(),
                updatedBook.getAuthor().getId(), updatedBook.getGenre().getId());

        Book actualBook = bookDaoJdbc.getById(updatedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(updatedBook);
    }

}