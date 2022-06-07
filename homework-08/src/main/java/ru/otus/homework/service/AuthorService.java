package ru.otus.homework.service;

import ru.otus.homework.domain.AuthorWithLinks;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorWithLinks> findAll();
    Optional<AuthorWithLinks> findById(String id);
    AuthorWithLinks insert(String name);
    AuthorWithLinks update(String id, String name);
}
