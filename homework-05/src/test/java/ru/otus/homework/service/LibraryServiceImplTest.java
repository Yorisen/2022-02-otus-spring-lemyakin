package ru.otus.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("Сервис library должен")
@SpringBootTest
class LibraryServiceImplTest {

    @Autowired
    LibraryServiceImpl libraryService;

    private static final BigDecimal NOT_EXISTING_ID = BigDecimal.valueOf(1000);
    private static final int EXPECTED_GENRES_COUNT = 3;
    private static final int EXPECTED_AUTHORS_COUNT = 3;
    private static final int EXPECTED_BOOKS_COUNT = 2;

    @DisplayName("отдавать все жанры")
    @Test
    void shouldGetAllGenres() {
        assertThat(libraryService.getGenres().size()).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("отдавать всех авторов")
    @Test
    void shouldGetAllAuthors() {
        assertThat(libraryService.getAuthors().size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("отдавать все книги")
    @Test
    void shouldGetAllBooks() {
        assertThat(libraryService.getBooks().size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("отдавать жанр по существующему id")
    @Test
    void shouldGetGenreByExistingId() {
        Genre existingGenre = Builder.buildExistingGenreWithoutLinkedBook();
        Genre actualGenre = libraryService.getGenreById(existingGenre.getId());
        assertThat(actualGenre).isEqualTo(existingGenre);
    }

    @DisplayName("отдавать null при запросе жанра по не существующему id")
    @Test
    void shouldGetNullByNotExistingGenreId() {
        Genre actualGenre = libraryService.getGenreById(NOT_EXISTING_ID);
        assertThat(actualGenre).isNull();
    }

    @DisplayName("отдавать автора по существующему id")
    @Test
    void shouldGetAuthorByExistingId() {
        Author existingAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        Author actualAuthor = libraryService.getAuthorById(existingAuthor.getId());
        assertThat(actualAuthor).isEqualTo(existingAuthor);
    }

    @DisplayName("отдавать null при запросе автора по не существующему id")
    @Test
    void shouldGetNullByNotExistingAuthorId() {
        Author actualAuthor = libraryService.getAuthorById(NOT_EXISTING_ID);
        assertThat(actualAuthor).isNull();
    }

    @DisplayName("отдавать книгу по существующему id")
    @Test
    void shouldGetBookByExistingId() {
        Book existingBook = Builder.buildExistingBook();
        Book actualBook = libraryService.getBookById(existingBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(existingBook);
    }

    @DisplayName("отдавать null при запросе книги по не существующему id")
    @Test
    void shouldGetNullByNotExistingBookId() {
        Book actualBook = libraryService.getBookById(NOT_EXISTING_ID);
        assertThat(actualBook).isNull();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("добавлять жанр")
    @Test
    void insertGenre() {
        Genre expectedGenre = new Genre(BigDecimal.valueOf(EXPECTED_GENRES_COUNT + 1), "Genre4");
        libraryService.insertGenre(expectedGenre.getId(), expectedGenre.getName());
        Genre actualGenre = libraryService.getGenreById(expectedGenre.getId());
        assertThat(actualGenre).isEqualTo(expectedGenre);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("добавлять автора")
    @Test
    void insertAuthor() {
        Author expectedAuthor = new Author(BigDecimal.valueOf(EXPECTED_AUTHORS_COUNT + 1), "Some", "Author",
                "WithPatronymic");

        libraryService.insertAuthor(expectedAuthor.getId(), expectedAuthor.getName(), expectedAuthor.getSurname(),
                expectedAuthor.getPatronymic());

        Author actualAuthor = libraryService.getAuthorById(expectedAuthor.getId());
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("добавлять книгу")
    @Test
    void insertBook() {
        Book existingBook = Builder.buildExistingBook();
        Book expectedBook = new Book(BigDecimal.valueOf(EXPECTED_BOOKS_COUNT + 1), "Book3",
                existingBook.getAuthor(), existingBook.getGenre());

        libraryService.insertBook(expectedBook.getId(), expectedBook.getName(),
                expectedBook.getAuthor().getId(), expectedBook.getGenre().getId());

        Book actualBook = libraryService.getBookById(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("удалять жанр")
    @Test
    void deleteGenreById() {
        Genre existingGenre = Builder.buildExistingGenreWithoutLinkedBook();
        assertThatCode(() -> libraryService.getGenreById(existingGenre.getId())).doesNotThrowAnyException();
        libraryService.deleteGenreById(existingGenre.getId());
        assertThat(libraryService.getGenreById(existingGenre.getId())).isNull();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("удалять автора")
    @Test
    void deleteAuthorById() {
        Author existingAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        assertThatCode(() -> libraryService.getAuthorById(existingAuthor.getId())).doesNotThrowAnyException();
        libraryService.deleteAuthorById(existingAuthor.getId());
        assertThat(libraryService.getAuthorById(existingAuthor.getId())).isNull();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("удалять книгу")
    @Test
    void deleteBookById() {
        Book existingBook = Builder.buildExistingBook();
        assertThatCode(() -> libraryService.getBookById(existingBook.getId())).doesNotThrowAnyException();
        libraryService.deleteBookById(existingBook.getId());
        assertThat(libraryService.getBookById(existingBook.getId())).isNull();
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @DisplayName("обновлять книгу")
    @Test
    void updateBook() {
        Book bookForUpdate = Builder.buildExistingBook();
        Book updatedBook = new Book(bookForUpdate.getId(), "UpdatedBookName",
                bookForUpdate.getAuthor(), bookForUpdate.getGenre());
        libraryService.updateBook(updatedBook.getId(), updatedBook.getName(),
                updatedBook.getAuthor().getId(), updatedBook.getGenre().getId());

        Book actualBook = libraryService.getBookById(updatedBook.getId());

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(updatedBook);
    }

}