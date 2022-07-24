package ru.otus.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.Author;

import java.math.BigDecimal;

public interface AuthorRepository extends JpaRepository<Author, BigDecimal> {
}
