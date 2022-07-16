package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.sql.Book;
import ru.otus.homework.repositories.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SqlBookServiceImpl implements SqlBookService {
    private final BookRepository bookRepository;

    @Override
     public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
