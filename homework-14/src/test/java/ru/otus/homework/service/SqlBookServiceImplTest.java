package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.sql.Book;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.repositories.BookRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@DisplayName("Сервис книг взаимодействующий в SQL БД должен")
@ExtendWith(MockitoExtension.class)
class SqlBookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private SqlBookServiceImpl bookService;

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

        List<Book> result = bookService.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

}