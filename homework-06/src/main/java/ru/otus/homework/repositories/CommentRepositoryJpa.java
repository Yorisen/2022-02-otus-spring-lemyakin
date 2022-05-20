package ru.otus.homework.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment findCommentById(BigDecimal id) {
        return em.find(Comment.class, id);
    }

    @Override
    public Comment insert(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        em.merge(comment);
        return comment;
    }

    @Override
    public void deleteById(BigDecimal id) {
        Comment comment = findCommentById(id);
        if (comment != null) {
            em.remove(comment);
        }
    }
}
