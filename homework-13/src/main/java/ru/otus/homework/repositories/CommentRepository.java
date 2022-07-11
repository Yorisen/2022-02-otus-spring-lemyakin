package ru.otus.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.Comment;

import java.math.BigDecimal;

public interface CommentRepository extends JpaRepository<Comment, BigDecimal> {

}
