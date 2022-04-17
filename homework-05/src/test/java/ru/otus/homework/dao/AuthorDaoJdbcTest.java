package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Builder;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    AuthorDaoJdbc authorDaoJdbc;

    private static final int EXPECTED_AUTHORS_COUNT = 3;
    private static final BigDecimal NOT_EXISTING_AUTHOR_ID = BigDecimal.valueOf(1000);

    @DisplayName("добавлять автора")
    @Test
    void shouldInsertAuthor() {
        Author expectedAuthor = new Author(BigDecimal.valueOf(EXPECTED_AUTHORS_COUNT + 1), "Some", "Author",
                "WithPatronymic");

        authorDaoJdbc.insert(expectedAuthor.getId(), expectedAuthor.getName(), expectedAuthor.getSurname(),
                expectedAuthor.getPatronymic());

        Author actualAuthor = authorDaoJdbc.getById(expectedAuthor.getId());
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("получать ошибку при попытке добавить автора с уже существующим id")
    @Test
    void shouldGetExceptionOnInsertAuthorWithExistingId() {
        Author existingAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        assertThatCode(() -> authorDaoJdbc.insert(existingAuthor.getId(), existingAuthor.getName(),
                existingAuthor.getSurname(), existingAuthor.getPatronymic()))
                .isInstanceOf(DuplicateKeyException.class);
    }

    @DisplayName("получать автора по существующему id")
    @Test
    void shouldGetAuthorById() {
        Author expectedAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        Author actualAuthor = authorDaoJdbc.getById(expectedAuthor.getId());
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("получать ошибку при попытке получить автора с не существующим id")
    @Test
    void shouldGetExceptionOnGettingAuthorWithNotExistingId() {
        assertThatCode(() -> authorDaoJdbc.getById(NOT_EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("получать всех авторов")
    @Test
    void shouldGetAllAuthors() {
        Author expectedAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        List<Author> actualAuthors = authorDaoJdbc.getAll();
        assertThat(actualAuthors.size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
        assertThat(actualAuthors).containsAnyOf(expectedAuthor);
    }

    @DisplayName("удалять автора по id")
    @Test
    void shouldDeleteAuthorById() {
        Author existingAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        assertThatCode(() -> authorDaoJdbc.getById(existingAuthor.getId())).doesNotThrowAnyException();
        authorDaoJdbc.deleteById(existingAuthor.getId());
        assertThatCode(() -> authorDaoJdbc.getById(existingAuthor.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}