package ru.otus.homework.repositories;

import ru.otus.homework.domain.Comment;

import java.math.BigDecimal;
import java.util.List;

public interface CommentRepository {
    List<Comment> findAll();
    Comment findCommentById(BigDecimal id);
    Comment insert(Comment comment);
    Comment update(Comment comment);
    void deleteById(BigDecimal id);
}
