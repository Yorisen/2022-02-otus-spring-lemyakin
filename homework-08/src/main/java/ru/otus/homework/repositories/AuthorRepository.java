package ru.otus.homework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.domain.AuthorWithLinks;

public interface AuthorRepository extends MongoRepository<AuthorWithLinks, String> {
}
