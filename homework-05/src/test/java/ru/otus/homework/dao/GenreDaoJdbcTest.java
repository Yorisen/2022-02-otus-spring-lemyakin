package ru.otus.homework.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    GenreDaoJdbc genreDaoJdbc;

    private static final int EXPECTED_GENRES_COUNT = 3;
    private static final BigDecimal NOT_EXISTING_GENRE_ID = BigDecimal.valueOf(1000);

    @DisplayName("добавлять жанр")
    @Test
    void shouldInsertGenre() {
        Genre expectedGenre = new Genre(BigDecimal.valueOf(EXPECTED_GENRES_COUNT + 1), "Genre4");
        genreDaoJdbc.insert(expectedGenre.getId(), expectedGenre.getName());
        Genre actualGenre = genreDaoJdbc.getById(expectedGenre.getId());
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @DisplayName("получать ошибку при попытке добавить жанр с уже существующим id")
    @Test
    void shouldGetExceptionOnInsertGenreWithExistingId() {
        Genre existingGenre = Builder.buildExistingGenreWithoutLinkedBook();
        assertThatCode(() -> genreDaoJdbc.insert(existingGenre.getId(), existingGenre.getName()))
                .isInstanceOf(DuplicateKeyException.class);
    }

    @DisplayName("получать жанр по существующему id")
    @Test
    void shouldGetGenreByExistingId() {
        Genre expectedGenre = Builder.buildExistingGenreWithoutLinkedBook();
        Genre actualGenre = genreDaoJdbc.getById(expectedGenre.getId());
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @DisplayName("получать ошибку при попытке получить жанр с не существующим id")
    @Test
    void shouldGetExceptionOnGettingGenreWithNotExistingId() {
        assertThatCode(() -> genreDaoJdbc.getById(NOT_EXISTING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("получать все жанры")
    @Test
    void shouldGetAllGenres() {
        Genre expectedGenre = Builder.buildExistingGenreWithoutLinkedBook();
        List<Genre> actualGenres = genreDaoJdbc.getAll();
        assertThat(actualGenres.size()).isEqualTo(EXPECTED_GENRES_COUNT);
        assertThat(actualGenres).containsAnyOf(expectedGenre);
    }

    @DisplayName("удалять жанр по id")
    @Test
    void shouldDeleteGenreById() {
        Genre existingGenre = Builder.buildExistingGenreWithoutLinkedBook();
        assertThatCode(() -> genreDaoJdbc.getById(existingGenre.getId())).doesNotThrowAnyException();
        genreDaoJdbc.deleteById(existingGenre.getId());
        assertThatCode(() -> genreDaoJdbc.getById(existingGenre.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}