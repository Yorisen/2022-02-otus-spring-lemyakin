package ru.otus.homework.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.domain.Comment;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий комментариев должен")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentRepositoryJpa commentRepository;

    private static final int EXPECTED_COMMENTS_FOR_BOOK_COUNT = 1;

    @Test
    @DisplayName("возвращать список всех комментариев")
    void shouldFindAllComments() {
        List<Comment> expectedComments = Builder.buildExistingCommentsList();
        List<Comment> actualComments = commentRepository.findAll();
        assertThat(actualComments.size()).isEqualTo(expectedComments.size());
    }

    @Test
    @DisplayName("возвращать комментарий по id")
    void shouldFindCommentByCommentId() {
        Comment expectedComment = Builder.buildExistingComment();
        Comment actualComment = commentRepository.findCommentById(expectedComment.getId());

        assertThat(actualComment).isNotNull()
                .hasFieldOrPropertyWithValue("nickname", expectedComment.getNickname())
                .hasFieldOrPropertyWithValue("content", expectedComment.getContent())
                .hasFieldOrPropertyWithValue("creationTimestamp", expectedComment.getCreationTimestamp());

        assertThat(actualComment.getBook().getId()).isEqualTo(expectedComment.getBook().getId());

    }

    @Test
    @DisplayName("добавлять комментарий")
    void shouldInsertComment() {
        Comment existingComment = Builder.buildExistingComment();
        Comment existingCommentFromDB = commentRepository.findCommentById(existingComment.getId());
        Book existingBook = existingCommentFromDB.getBook();

        Comment newComment = new Comment();
        newComment.setNickname("NewNick");
        newComment.setContent("New comment");
        newComment.setCreationTimestamp(Instant.now());
        newComment.setBook(existingBook);

        commentRepository.insert(newComment);

        List<Comment> bookComments = existingCommentFromDB.getBook().getComments();
        for (Comment bookComment: bookComments) {
            if (bookComment.getContent().equals(newComment.getContent())) {
                assertThat(bookComment)
                        .hasFieldOrPropertyWithValue("nickname", newComment.getNickname())
                        .hasFieldOrPropertyWithValue("content", newComment.getContent())
                        .hasFieldOrPropertyWithValue("creationTimestamp", newComment.getCreationTimestamp());
                break;
            }
        }

    }

    @Test
    @DisplayName("обновлять комментарий")
    void shouldUpdateComment() {
        Comment expectedComment = Builder.buildExistingComment();
        expectedComment.setContent("New content");
        commentRepository.update(expectedComment);
        Comment actualComment = commentRepository.findCommentById(expectedComment.getId());
        assertThat(actualComment.getContent()).isEqualTo(expectedComment.getContent());
    }

    @Test
    @DisplayName("удалять комментарий по id")
    void shouldDeleteCommentById() {
        Comment existingComment = Builder.buildExistingComment();
        Comment existingCommentFromDB = commentRepository.findCommentById(existingComment.getId());
        Book existingBook = existingCommentFromDB.getBook();

        Comment newComment = new Comment();
        newComment.setNickname("NewNickForDeletion");
        newComment.setContent("New comment for deletion");
        newComment.setBook(existingBook);

        commentRepository.insert(newComment);
        Comment insertedComment = commentRepository.findCommentById(newComment.getId());
        assertThat(insertedComment).isNotNull();

        commentRepository.deleteById(insertedComment.getId());
        Comment deletedComment = commentRepository.findCommentById(newComment.getId());
        assertThat(deletedComment).isNull();
    }
}