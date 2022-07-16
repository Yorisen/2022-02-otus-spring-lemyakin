package ru.otus.homework.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.domain.nosql.Book;

public interface NoSqlBookRepository extends MongoRepository<Book, String> {
}
