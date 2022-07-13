package ru.otus.homework.service;

import ru.otus.homework.domain.sql.Book;
import java.util.List;

public interface SqlBookService {
    List<Book> findAll();
}
