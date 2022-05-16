package ru.otus.homework.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Builder;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {
    @Autowired
    EntityManager em;

    @Autowired
    BookRepositoryJpa bookRepository;

    private static final int EXPECTED_BOOKS_COUNT = 2;

    @DisplayName("добавлять книгу")
    @Test
    void shouldInsertBook() {
        Book bookForInsert = Builder.getNewBookWithoutComments("Book for insert", "Test genre",
                "Ivan", "Ivanov", "Ivanovich");
        bookRepository.insert(bookForInsert);

        Book insertedBook = bookRepository.findById(bookForInsert.getId());
        assertThat(insertedBook).isNotNull();
    }

    @DisplayName("получать книгу по существующему id")
    @Test
    void shouldGetBookByExistingId() {
        Book existingBook = Builder.buildExistingBook();
        Book actualBook = bookRepository.findById(existingBook.getId());
        assertThat(actualBook).isNotNull()
                .hasFieldOrPropertyWithValue("name", existingBook.getName())
                .hasFieldOrPropertyWithValue("genre", existingBook.getGenre())
                .hasFieldOrPropertyWithValue("author", existingBook.getAuthor());
    }

    @DisplayName("получать все книги")
    @Test
    void shouldGetAllBooks() {
        Book existingBook = Builder.buildExistingBook();
        Book expectedBook = bookRepository.findById(existingBook.getId());
        List<Book> actualBooks = bookRepository.findAll();
        assertThat(actualBooks.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
        assertThat(actualBooks).containsAnyOf(expectedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        Book bookForInsert = Builder.getNewBookWithoutComments("Book for insert", "Test genre",
                "Ivan", "Ivanov", "Ivanovich");
        bookRepository.insert(bookForInsert);

        Book insertedBook = bookRepository.findById(bookForInsert.getId());
        assertThat(insertedBook).isNotNull();

        bookRepository.deleteById(insertedBook.getId());
        Book deletedBook = bookRepository.findById(insertedBook.getId());
        assertThat(deletedBook).isNull();
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldUpdateBook() {
        Book bookForUpdate = Builder.buildExistingBook();
        bookForUpdate.setName("Updated name");

        bookRepository.update(bookForUpdate);

        Book actualBook = bookRepository.findById(bookForUpdate.getId());
        assertThat(actualBook.getName()).isEqualTo(bookForUpdate.getName());
    }
}