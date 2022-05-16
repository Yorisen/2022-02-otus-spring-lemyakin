package ru.otus.homework.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }

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
