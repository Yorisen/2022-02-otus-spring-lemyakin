package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.repositories.GenreRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис книг должен")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final BigDecimal BOOK_FIRST_ID = BigDecimal.valueOf(1);
    private static final BigDecimal BOOK_SECOND_ID = BigDecimal.valueOf(2);

    Book book;

    @BeforeEach
    public void setup() {
        book = Builder.buildExistingBook();
        book.setId(BOOK_FIRST_ID);
    }

    @DisplayName("получать 2 элемента при запросе всех книг")
    @Test
    void shouldGet2ElementsOnGettingAllBooks() {
        Book secondBook = Builder.buildExistingBook();
        secondBook.setId(BOOK_SECOND_ID);

        given(bookRepository.findAll()).willReturn(List.of(book, secondBook));

        List<Book> result = bookRepository.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("получать книгу по существующему id")
    @Test
    void shouldGetBookByExistingId() {
        given(bookRepository.findById(book.getId())).willReturn(book);
        Book actualBook = bookService.findById(book.getId());
        assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("добавлять книгу")
    @Test
    void shouldInsertBook() {
        Book newBook = Builder.buildExistingBook();
        newBook.setId(null);
        newBook.setComments(null);

        given(authorRepository.findById(newBook.getAuthor().getId())).willReturn(book.getAuthor());
        given(genreRepository.findById(newBook.getGenre().getId())).willReturn(book.getGenre());
        given(bookRepository.insert(newBook)).willReturn(book);
        Book actualBook = bookService.insert(newBook.getName(), newBook.getAuthor().getId(), newBook.getGenre().getId());
        assertThat(actualBook).isEqualTo(book);
    }

    @DisplayName("обновлять книгу")
    @Test
    void shouldUpdateBook() {
        Book bookForUpdate = Builder.buildExistingBook();
        bookForUpdate.setName("Some updated name");
        bookForUpdate.setComments(null);

        Book updatedBook = Builder.buildExistingBook();
        updatedBook.setName(bookForUpdate.getName());
        updatedBook.setComments(null);

        book.setComments(null);

        given(authorRepository.findById(bookForUpdate.getAuthor().getId())).willReturn(book.getAuthor());
        given(genreRepository.findById(bookForUpdate.getGenre().getId())).willReturn(book.getGenre());
        given(bookRepository.findById(bookForUpdate.getId())).willReturn(book);
        given(bookRepository.update(bookForUpdate)).willReturn(updatedBook);
        Book actualBook = bookService.update(bookForUpdate.getId(), bookForUpdate.getName(),
                bookForUpdate.getAuthor().getId(), bookForUpdate.getGenre().getId());

        assertThat(actualBook).isEqualTo(updatedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        willDoNothing().given(bookRepository).deleteById(book.getId());
        bookRepository.deleteById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
    }
}