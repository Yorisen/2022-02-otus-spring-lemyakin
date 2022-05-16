package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.repositories.CommentRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(BigDecimal id) {
        return commentRepository.findCommentById(id);
    }

    @Override
    public Comment insert(String nickname, String content, Instant creationTimestamp, BigDecimal bookId) {
        Comment insertedComment = null;

        Book bookForComment = bookRepository.findById(bookId);
        if (bookForComment != null) {
            Comment commentForInsert = new Comment();
            commentForInsert.setNickname(nickname);
            commentForInsert.setContent(content);
            commentForInsert.setCreationTimestamp(creationTimestamp);
            commentForInsert.setBook(bookForComment);

            insertedComment = commentRepository.insert(commentForInsert);
        }

        return insertedComment;
    }

    @Override
    public Comment update(BigDecimal id, String nickname, String content) {
        Comment updatedComment = null;

        Comment commentForUpdate = commentRepository.findCommentById(id);
        if (commentForUpdate != null) {
            commentForUpdate.setNickname(nickname);
            commentForUpdate.setContent(content);

            updatedComment = commentRepository.update(commentForUpdate);
        }

        return updatedComment;
    }

    @Override
    public void deleteById(BigDecimal id) {
        commentRepository.deleteById(id);
    }
}
