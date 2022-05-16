package ru.otus.homework.service;

import ru.otus.homework.domain.Comment;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findById(BigDecimal id);
    Comment insert(String nickname, String content, Instant creationTimestamp, BigDecimal bookId);
    Comment update(BigDecimal id, String nickname, String content);
    void deleteById(BigDecimal id);
}
