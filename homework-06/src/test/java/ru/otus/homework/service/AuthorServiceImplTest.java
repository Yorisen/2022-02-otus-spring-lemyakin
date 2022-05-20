package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Author;
import ru.otus.homework.repositories.AuthorRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис авторов должен")
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private static final int EXPECTED_AUTHORS_COUNT = 2;
    private static final BigDecimal AUTHOR_FIRST_ID = BigDecimal.valueOf(1);
    private static final BigDecimal AUTHOR_SECOND_ID = BigDecimal.valueOf(2);

    private Author author;

    @BeforeEach
    public void setup() {
        author = new Author(AUTHOR_FIRST_ID, "TestName", "TestSurname", "TestPatronymic");
    }

    @DisplayName("получать 2 элемента при запросе всех авторов")
    @Test
    void shouldGet2ElementsOnGettingAllAuthors() {
        Author secondAuthor = new Author(AUTHOR_SECOND_ID, "SecondTestName", "SecondTestSurname",
                "SecondTestPatronymic");

        given(authorRepository.findAll()).willReturn(List.of(author, secondAuthor));

        List<Author> result = authorService.findAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("получать автора по id")
    @Test
    void shouldGetAuthorById() {
        given(authorRepository.findById(author.getId())).willReturn(author);
        Author actualAuthor = authorService.findById(author.getId());
        assertThat(actualAuthor).isEqualTo(author);
    }

    @DisplayName("добавлять автора")
    @Test
    void shouldInsertAuthor() {
        Author newAuthor = new Author(null, author.getName(), author.getSurname(), author.getPatronymic());
        given(authorRepository.insert(newAuthor)).willReturn(author);

        Author actualAuthor = authorService.insert(author.getName(), author.getSurname(), author.getPatronymic());
        assertThat(actualAuthor).isEqualTo(author);
    }

    @DisplayName("удалять автора по id")
    @Test
    void shouldDeleteAuthorById() {
        willDoNothing().given(authorRepository).deleteById(author.getId());
        authorService.deleteById(author.getId());
        verify(authorRepository, times(1)).deleteById(author.getId());
    }
}