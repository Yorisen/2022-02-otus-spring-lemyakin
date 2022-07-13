package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.nosql.Book;
import ru.otus.homework.repositories.NoSqlBookRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class NoSqlBookServiceImpl implements NoSqlBookService {
    private final NoSqlBookRepository bookRepository;

    @Override
     public List<Book> findAll() {
        return bookRepository.findAll();
    }


}
