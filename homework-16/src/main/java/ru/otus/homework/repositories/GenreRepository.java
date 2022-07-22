package ru.otus.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;

public interface GenreRepository extends JpaRepository<Genre, BigDecimal> {

}
