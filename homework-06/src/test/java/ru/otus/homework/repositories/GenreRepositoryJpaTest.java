package ru.otus.homework.repositories;

import liquibase.pro.packaged.G;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий жанров должен")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {
    @Autowired
    private GenreRepositoryJpa genreRepository;

    @Autowired
    private TestEntityManager em;

    private static final int EXPECTED_GENRES_COUNT = 3;

    @DisplayName("добавлять жанр")
    @Test
    void shouldInsertGenre() {
        Genre genreForInsert = Builder.getNewGenre("Some test genre");

        genreRepository.insert(genreForInsert);
        Genre insertedGenre = genreRepository.findById(genreForInsert.getId());

        assertThat(insertedGenre).isNotNull();
    }

    @DisplayName("получать жанр по существующему id")
    @Test
    void shouldGetGenreByExistingId() {
        Genre existingGenre = Builder.buildExistingGenre();
        Genre actualGenre = genreRepository.findById(existingGenre.getId());
        assertThat(actualGenre).isEqualTo(existingGenre);
    }

    @DisplayName("получать все жанры")
    @Test
    void shouldGetAllGenres() {
        Genre expectedGenre = Builder.buildExistingGenreWithoutLinkedBook();
        List<Genre> actualGenres = genreRepository.findAll();
        assertThat(actualGenres.size()).isEqualTo(EXPECTED_GENRES_COUNT);
        assertThat(actualGenres).containsAnyOf(expectedGenre);
    }

    @DisplayName("удалять жанр по id")
    @Test
    void shouldDeleteGenreById() {
        Genre genreForInsert = Builder.getNewGenre("Some test genre");

        genreRepository.insert(genreForInsert);
        Genre insertedGenre = genreRepository.findById(genreForInsert.getId());
        assertThat(insertedGenre).isNotNull();

        genreRepository.deleteById(insertedGenre.getId());
        Genre deletedGenre = genreRepository.findById(insertedGenre.getId());
        assertThat(deletedGenre).isNull();
    }
}