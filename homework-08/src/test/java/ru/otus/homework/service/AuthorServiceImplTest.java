package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.*;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис авторов должен")
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private final int EXPECTED_AUTHORS_COUNT = 1;

    AuthorWithLinks existingAuthor;
    Optional<AuthorWithLinks> existingAuthorOptional;
    Book book;
    Optional<Book> bookOptional;

    @BeforeEach
    public void setup() {
        existingAuthor = Builder.buildExistingAuthorWithLinks();
        existingAuthorOptional = Optional.ofNullable(existingAuthor);
        book = Builder.buildExistingBook();
        bookOptional = Optional.ofNullable(book);
    }

    @Test
    @DisplayName("получать 1 элемент при запросе всех авторов")
    void shouldGet1ElementOnGettingAllAuthors() {
        given(authorRepository.findAll()).willReturn(List.of(existingAuthor));
        List<AuthorWithLinks> authors = authorService.findAll();
        assertThat(authors.size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("получать автора по существующему id")
    @Test
    void shouldGetAuthorByExistingId() {
        given(authorRepository.findById(existingAuthor.getId())).willReturn(existingAuthorOptional);
        Optional<AuthorWithLinks> actualAuthorOptional = authorService.findById(existingAuthor.getId());
        AuthorWithLinks actualAuthor = actualAuthorOptional.get();
        assertThat(actualAuthor).isEqualTo(existingAuthor);
    }

    @DisplayName("добавлять автора")
    @Test
    void insert() {
        AuthorWithLinks newAuthor = new AuthorWithLinks(null, "TestAuthor", new ArrayList<>());
        AuthorWithLinks expectedAuthor = new AuthorWithLinks("11", "TestAuthor", new ArrayList<>());

        given(authorRepository.insert(newAuthor)).willReturn(expectedAuthor);
        AuthorWithLinks actualAuthor = authorService.insert(expectedAuthor.getName());
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @Test
    void update() {
        AuthorWithLinks updatedAuthor = Builder.buildExistingAuthorWithLinks();
        updatedAuthor.setName("NewAuthorName");
        Book updatedBook = Builder.buildExistingBook();
        updatedBook.setAuthor(new Author(updatedAuthor.getId(), updatedAuthor.getName()));

        given(authorRepository.findById(existingAuthor.getId())).willReturn(existingAuthorOptional);
        given(authorRepository.save(updatedAuthor)).willReturn(updatedAuthor);
        given(bookRepository.findById(book.getId())).willReturn(bookOptional);
        given(bookRepository.save(updatedBook)).willReturn(updatedBook);

        AuthorWithLinks actualAuthor = authorService.update(existingAuthor.getId(), updatedAuthor.getName());
        assertThat(actualAuthor).isEqualTo(updatedAuthor);

    }
}