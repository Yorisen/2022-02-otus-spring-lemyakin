package ru.otus.homework.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.sql.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, BigDecimal> {
    @Override
    @EntityGraph(attributePaths = {"author","genre"})
    List<Book> findAll();

}
