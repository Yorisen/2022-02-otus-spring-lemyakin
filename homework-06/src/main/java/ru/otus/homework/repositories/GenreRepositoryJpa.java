package ru.otus.homework.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Genre insert(Genre genre) {
        em.persist(genre);
        return genre;
    }

    @Override
    public Genre findById(BigDecimal id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public void deleteById(BigDecimal id) {
        Genre genreForDelete = findById(id);
        if (genreForDelete != null) {
            em.remove(genreForDelete);
        }
    }
}
