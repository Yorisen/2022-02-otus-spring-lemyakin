package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.repositories.CommentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис комментариев должен")
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;
    private Optional<Comment> commentOptional;
    private Optional<Book> bookOptional;
    private static final int EXPECTED_COMMENTS_COUNT = 1;
    private static final BigDecimal COMMENT_FIRST_ID = BigDecimal.valueOf(1);

    @BeforeEach
    public void setup() {
        comment = Builder.buildExistingComment();
        comment.setId(COMMENT_FIRST_ID);
        commentOptional = Optional.ofNullable(comment);

        bookOptional = Optional.ofNullable(comment.getBook());
    }

    @DisplayName("получать 2 элемента при запросе всех комментариев")
    @Test
    void shouldGet2ElementsOnGettingAllComments() {

        given(bookRepository.findById(COMMENT_FIRST_ID)).willReturn(bookOptional);

        List<Comment> result = commentService.findCommentsByBookId(COMMENT_FIRST_ID);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(EXPECTED_COMMENTS_COUNT);
    }

    @Test
    @DisplayName("возвращать комментарий по id")
    void shouldReturnCommentByCommentId() {
        given(commentRepository.findById(comment.getId())).willReturn(commentOptional);
        Optional<Comment> actualComment = commentService.findById(comment.getId());
        assertThat(actualComment.isPresent()).isTrue();
        assertThat(actualComment.get())
                .hasFieldOrPropertyWithValue("nickname", comment.getNickname())
                .hasFieldOrPropertyWithValue("content", comment.getContent())
                .hasFieldOrPropertyWithValue("creationTimestamp", comment.getCreationTimestamp());
    }

    @Test
    @DisplayName("добавлять комментарий")
    void shouldInsertComment() {
        Comment newComment = Builder.buildExistingComment();
        newComment.setId(null);
        newComment.getBook().setComments(new ArrayList<>());

        given(bookRepository.findById(comment.getBook().getId())).willReturn(bookOptional);
        given(commentRepository.save(newComment)).willReturn(comment);

        Comment actualComment = commentService.insert(newComment.getNickname(), newComment.getContent(),
                newComment.getCreationTimestamp(), newComment.getBook().getId());
        assertThat(actualComment).isNotNull()
                .hasFieldOrPropertyWithValue("id", comment.getId())
                .hasFieldOrPropertyWithValue("nickname", comment.getNickname())
                .hasFieldOrPropertyWithValue("content", comment.getContent())
                .hasFieldOrPropertyWithValue("creationTimestamp", comment.getCreationTimestamp());
    }

    @Test
    @DisplayName("обновлять комментарий")
    void shouldUpdateComment() {
        Comment commentForUpdate = Builder.buildExistingComment();
        commentForUpdate.setContent("Some update");
        commentForUpdate.getBook().setComments(new ArrayList<>());

        comment.getBook().setComments(new ArrayList<>());

        given(commentRepository.findById(comment.getId())).willReturn(commentOptional);
        given(commentRepository.save(commentForUpdate)).willReturn(commentForUpdate);

        Comment actualComment = commentService.update(commentForUpdate.getId(), commentForUpdate.getNickname(),
                commentForUpdate.getContent());

        assertThat(actualComment).isNotNull()
                .hasFieldOrPropertyWithValue("content", commentForUpdate.getContent());
    }

    @Test
    @DisplayName("удалять комментарий по id")
    void shouldDeleteCommentById() {
        willDoNothing().given(commentRepository).deleteById(comment.getId());
        commentService.deleteById(comment.getId());
        verify(commentRepository, times(1)).deleteById(comment.getId());
    }
}