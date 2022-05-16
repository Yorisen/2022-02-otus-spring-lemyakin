package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.service.CommentService;
import ru.otus.homework.shell.viewer.CommentsViewer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentServiceCommands {
    private final CommentService commentService;
    private final CommentsViewer commentsViewer;

    @Transactional(readOnly=true)
    @ShellMethod(value = "Get all comments", key = {"comments", "ca"})
    public void getComments() {
        List<Comment> comments = commentService.findAll();
        commentsViewer.view(comments);
    }

    @Transactional(readOnly=true)
    @ShellMethod(value = "Get comment by id", key = {"comment", "c"})
    public void getCommentById(@ShellOption({"-i", "--id"}) BigDecimal id) {
        Comment comment = commentService.findById(id);
        commentsViewer.view(comment);
    }

    @Transactional
    @ShellMethod(value = "Insert new comment", key = {"comment_insert", "ci"})
    public void insertComment(@ShellOption({"-bi", "--book_id"}) BigDecimal bookId,
                              @ShellOption({"-n", "--nickname"}) String nickname,
                              @ShellOption({"-c", "--content"}) String content) {
        commentService.insert(nickname, content, Instant.now(), bookId);
    }

    @Transactional
    @ShellMethod(value = "Delete comment", key = {"comment_delete", "cd"})
    public void deleteComment(@ShellOption({"-i", "--id"}) BigDecimal id) {
        commentService.deleteById(id);
    }

    @Transactional
    @ShellMethod(value = "Update comment", key = {"comment_update", "cu"})
    public void updateComment(@ShellOption({"-i", "--id"}) BigDecimal id,
                              @ShellOption({"-n", "--nickname"}) String nickname,
                              @ShellOption({"-c", "--content"}) String content) {
        commentService.update(id, nickname, content);
    }
}
