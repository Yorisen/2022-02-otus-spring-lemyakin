package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Author;
import ru.otus.homework.repositories.AuthorRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(BigDecimal id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author insert(String name, String surname, String patronymic) {
        Author newAuthor = new Author();
        newAuthor.setName(name);
        newAuthor.setSurname(surname);
        newAuthor.setPatronymic(patronymic);

        return authorRepository.insert(newAuthor);
    }

    @Override
    public void deleteById(BigDecimal id) {
        authorRepository.deleteById(id);
    }
}
