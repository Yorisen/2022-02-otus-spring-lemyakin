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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

    @InjectMocks
    private BookServiceImpl bookService;

    private static final int EXPECTED_BOOKS_COUNT = 2;
    private static final String BOOK_SECOND_ID = "2";

    Book book;
    Comment comment;
    Optional<Genre> genreOptional;
    Optional<Book> bookOptional;

    AuthorWithLinks existingAuthor;
    Optional<AuthorWithLinks> existingAuthorOptional;

    @BeforeEach
    public void setup() {
        book = Builder.buildExistingBook();
        bookOptional = Optional.ofNullable(book);
        genreOptional = Optional.ofNullable(book.getGenre());
        comment = new Comment("1", "TestNickname", "Some comment", Instant.now());
        existingAuthor = Builder.buildExistingAuthorWithLinks();
        existingAuthorOptional = Optional.ofNullable(existingAuthor);
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
        given(bookRepository.findById(book.getId())).willReturn(bookOptional);
        Optional<Book> actualBook = bookService.findById(book.getId());
        assertThat(actualBook).isEqualTo(bookOptional);
    }

    @DisplayName("добавлять книгу")
    @Test
    void shouldInsertBook() {
        Book newBook = Builder.buildExistingBook();
        newBook.setId(null);
        newBook.setComments(null);

        given(bookRepository.save(newBook)).willReturn(book);
        given(authorRepository.findById(existingAuthor.getId())).willReturn(existingAuthorOptional);
        Book actualBook = bookService.insert(newBook.getName(), newBook.getAuthor().getId(), newBook.getGenre().getName());
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

        given(bookRepository.findById(bookForUpdate.getId())).willReturn(bookOptional);
        given(bookRepository.save(bookForUpdate)).willReturn(updatedBook);
        given(authorRepository.findById(existingAuthor.getId())).willReturn(existingAuthorOptional);

        Book actualBook = bookService.update(bookForUpdate.getId(), bookForUpdate.getName(),
                bookForUpdate.getAuthor().getId(), bookForUpdate.getGenre().getName());

        assertThat(actualBook).isEqualTo(updatedBook);
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        willDoNothing().given(bookRepository).deleteById(book.getId());
        bookService.deleteById(book.getId());
        verify(bookRepository, times(1)).deleteById(book.getId());
    }

    @DisplayName("добавлять комментарий к книге")
    @Test
    void shouldAddCommentToBook() {
        given(bookRepository.findById(book.getId())).willReturn(bookOptional);

        Book bookWithAddedComment = Builder.buildExistingBook();
        bookWithAddedComment.setComments(List.of(comment));
        given(bookRepository.save(book)).willReturn(bookWithAddedComment);

        Book actualBook = bookService.addComment(book.getId(), comment);
        assertThat(actualBook.getComments()).contains(comment);
    }

    @DisplayName("удалять комментарий к книге")
    @Test
    void shouldDeleteBookComment() {
        given(bookRepository.findById(book.getId())).willReturn(bookOptional);

        Book bookWithAddedComment = Builder.buildExistingBook();
        bookWithAddedComment.setComments(List.of(comment));
        given(bookRepository.save(book)).willReturn(bookWithAddedComment);

        Book actualBookWithComment = bookService.addComment(book.getId(), comment);
        assertThat(actualBookWithComment.getComments()).contains(comment);

        Book bookWithoutComment = Builder.buildExistingBook();
        given(bookRepository.save(book)).willReturn(bookWithoutComment);
        Book actualBookWithoutComments = bookService.deleteComment(actualBookWithComment.getId(), comment.getId());
        assertThat(actualBookWithoutComments.getComments()).isEmpty();
    }

    @DisplayName("изменять комментарий к книге")
    @Test
    void shouldUpdateBookComment() {
        given(bookRepository.findById(book.getId())).willReturn(bookOptional);

        Book bookWithAddedComment = Builder.buildExistingBook();
        bookWithAddedComment.setComments(List.of(comment));
        given(bookRepository.save(book)).willReturn(bookWithAddedComment);

        Book actualBook = bookService.addComment(book.getId(), comment);
        assertThat(actualBook.getComments()).contains(comment);

        Book bookWithUpdatedComment = Builder.buildExistingBook();
        Comment updatedComment = new Comment(comment.getId(), "UpdatedNickname",
                "UpdatedComment", comment.getCreationTimestamp());
        bookWithUpdatedComment.setComments(List.of(updatedComment));

        given(bookRepository.save(book)).willReturn(bookWithUpdatedComment);
        Book actualBookWithUpdatedComment = bookService.updateComment(book.getId(), comment.getId(),
                updatedComment.getNickname(), updatedComment.getContent());
        assertThat(actualBookWithUpdatedComment.getComments().get(0))
                .hasFieldOrPropertyWithValue("id", updatedComment.getId())
                .hasFieldOrPropertyWithValue("nickname", updatedComment.getNickname())
                .hasFieldOrPropertyWithValue("content", updatedComment.getContent())
                .hasFieldOrPropertyWithValue("creationTimestamp", updatedComment.getCreationTimestamp());
    }
}