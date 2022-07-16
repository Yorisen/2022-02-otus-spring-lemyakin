package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.NoSqlBuilder;
import ru.otus.homework.domain.nosql.Book;
import ru.otus.homework.repositories.NoSqlBookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@DisplayName("Сервис книг взаимодействующий в NOSQL БД должен")
@ExtendWith(MockitoExtension.class)
class NoSqlBookServiceImplTest {
    @Mock
    private NoSqlBookRepository bookRepository;

    @InjectMocks
    private NoSqlBookServiceImpl bookService;

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final String BOOK_FIRST_ID = "1";
    private static final String BOOK_SECOND_ID = "2";

    Book book;

    @BeforeEach
    public void setup() {
        book = NoSqlBuilder.buildExistingBook();
        book.setId(BOOK_FIRST_ID);
    }

    @DisplayName("получать 2 элемента при запросе всех книг")
    @Test
    void shouldGet2ElementsOnGettingAllBooks() {
        Book secondBook = NoSqlBuilder.buildExistingBook();
        secondBook.setId(BOOK_SECOND_ID);

        given(bookRepository.findAll()).willReturn(List.of(book, secondBook));

        List<Book> result = bookService.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

}