package ru.otus.homework.repositories;

import ru.otus.homework.domain.Comment;

import java.math.BigDecimal;

public interface CommentRepository {
    Comment findCommentById(BigDecimal id);
    Comment insert(Comment comment);
    Comment update(Comment comment);
    void deleteById(BigDecimal id);
}
