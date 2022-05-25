package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.repositories.BookRepository;
import ru.otus.homework.repositories.CommentRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(BigDecimal id) {
        List<Comment> bookComments = new ArrayList<>();
        Optional<Book> foundBookOptional = bookRepository.findById(id);
        if (foundBookOptional.isPresent()) {
            Book foundBook = foundBookOptional.get();
            if (foundBook.getComments() != null && !foundBook.getComments().isEmpty()) {
                bookComments = foundBook.getComments();
            }
        }

        return bookComments;
    }

    @Override
    public Optional<Comment> findById(BigDecimal id) {
        return commentRepository.findById(id);
    }



    @Override
    @Transactional
    public Comment insert(String nickname, String content, Instant creationTimestamp, BigDecimal bookId) {
        Comment insertedComment = null;

        Optional<Book> bookForComment = bookRepository.findById(bookId);
        if (bookForComment.isPresent()) {
            Comment commentForInsert = new Comment();
            commentForInsert.setNickname(nickname);
            commentForInsert.setContent(content);
            commentForInsert.setCreationTimestamp(creationTimestamp);
            commentForInsert.setBook(bookForComment.get());
            bookForComment.get().setComments(new ArrayList<>());

            insertedComment = commentRepository.save(commentForInsert);
        }

        return insertedComment;
    }

    @Override
    @Transactional
    public Comment update(BigDecimal id, String nickname, String content) {
        Comment updatedComment = null;

        Optional<Comment> commentForUpdateOptional = commentRepository.findById(id);
        if (commentForUpdateOptional.isPresent()) {
            Comment commentForUpdate = commentForUpdateOptional.get();
            commentForUpdate.setNickname(nickname);
            commentForUpdate.setContent(content);

            updatedComment = commentRepository.save(commentForUpdate);
        }

        return updatedComment;
    }

    @Override
    @Transactional
    public void deleteById(BigDecimal id) {
        commentRepository.deleteById(id);
    }
}
