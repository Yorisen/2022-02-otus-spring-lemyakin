package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.GenreRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис жанров должен")
@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    private static final int EXPECTED_GENRES_COUNT = 2;
    private static final BigDecimal GENRE_FIRST_ID = BigDecimal.valueOf(1);
    private static final BigDecimal GENRE_SECOND_ID = BigDecimal.valueOf(2);

    private Genre genre;
    private Optional<Genre> genreOptional;

    @BeforeEach
    public void setup() {
        genre = new Genre(GENRE_FIRST_ID, "Test genre");
        genreOptional = Optional.of(genre);
    }

    @DisplayName("получать 2 элемента при запросе всех")
    @Test
    void shouldReturn2ElementsOnGettingAllGenres() {
        Genre secondGenre = new Genre(GENRE_SECOND_ID, "Second test genre");

        given(genreRepository.findAll()).willReturn(List.of(genre, secondGenre));

        List<Genre> result = genreService.getAll();
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("получать жанр по id")
    @Test
    void shouldGetGenreById() {
        given(genreRepository.findById(genre.getId())).willReturn(genreOptional);

        Optional<Genre> actualGenre = genreService.getById(genre.getId());
        assertThat(actualGenre).isEqualTo(genreOptional);
    }

    @DisplayName("добавлять жанр")
    @Test
    void shouldInsertGenre() {
        Genre newGenre = new Genre(null, genre.getName());
        given(genreRepository.save(newGenre)).willReturn(genre);

        Genre actualGenre = genreService.insert(genre.getName());
        assertThat(actualGenre).isEqualTo(genre);
    }

    @DisplayName("удалять жанр по id")
    @Test
    void shouldDeleteGenreById() {
        willDoNothing().given(genreRepository).deleteById(genre.getId());

        genreService.deleteById(genre.getId());

        verify(genreRepository, times(1)).deleteById(genre.getId());
    }
}