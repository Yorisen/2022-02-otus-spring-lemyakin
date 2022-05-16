package ru.otus.homework.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book insert(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Book findById(BigDecimal id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b " +
                                "join fetch b.author a " +
                                "join fetch b.genre g", Book.class).getResultList();
    }

    @Override
    public void deleteById(BigDecimal id) {
        Book bookForDelete = findById(id);
        if (bookForDelete != null) {
            em.remove(bookForDelete);
        }
    }

    @Override
    public Book update(Book book) {
        em.merge(book);
        return book;
    }
}
