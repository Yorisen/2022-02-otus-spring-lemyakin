package ru.otus.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.domain.User;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, BigDecimal> {
}
