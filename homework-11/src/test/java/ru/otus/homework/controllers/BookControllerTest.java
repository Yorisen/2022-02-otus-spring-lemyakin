package ru.otus.homework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.domain.Book;
import ru.otus.homework.repositories.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DisplayName("Контроллер книг должен")
@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    Book existingBook;
    Mono<Book> monoExistingBook;

    @BeforeEach
    public void setup() {
        existingBook = new Book("1", "Book", "Author", "Genre");
        monoExistingBook = Mono.just(existingBook);
    }

    @DisplayName("получать 2 элемента при запросе всех книг")
    @Test
    void shouldGet2ElementsOnGettingAllBooks() {
        Book secondExistingBook = new Book("2", "Book2", "Author2", "Genre2");
        Flux<Book> existingBooks = Flux.just(existingBook, secondExistingBook);
        given(bookRepository.findAll()).willReturn(existingBooks);

        Flux<Book> actualBooks = bookController.getBooks();
        assertThat(actualBooks).isEqualTo(existingBooks);
    }

    @DisplayName("получать книгу по существующему id")
    @Test
    void shouldGetBookByExistingId() {
        given(bookRepository.findById(existingBook.getId())).willReturn(monoExistingBook);
        Mono<Book> actualBook = bookController.getBook(existingBook.getId());
        assertThat(actualBook.block()).isEqualTo(monoExistingBook.block());
    }

    @DisplayName("добавлять книгу")
    @Test
    void shouldCreateBook() {
        Book newBook = new Book(null, "Book3", "Author3", "Genre3");
        Mono<Book> newBookInserted = Mono.just(new Book("3", "Book3", "Author3", "Genre3"));

        given(bookRepository.insert(newBook)).willReturn(newBookInserted);
        Mono<Book> actualBook = bookController.createBook(newBook);
        assertThat(actualBook.block()).isEqualTo(newBookInserted.block());
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldUpdateBook() {
        existingBook.setName("Updated");

        given(bookRepository.save(existingBook)).willReturn(monoExistingBook);
        Mono<Book> actualBook = bookController.updateBook(existingBook);
        assertThat(actualBook.block()).isEqualTo(monoExistingBook.block());
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        Mono<Void> expected = Mono.empty();
        given(bookRepository.deleteById(existingBook.getId())).willReturn(expected);
        Mono<Void> actual = bookController.deleteBook(existingBook.getId());
        assertThat(actual.block()).isEqualTo(expected.block());
    }
}