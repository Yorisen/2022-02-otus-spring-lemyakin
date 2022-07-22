package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Author;
import ru.otus.homework.repositories.AuthorRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(BigDecimal id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public Author insert(String name, String surname, String patronymic) {
        Author newAuthor = new Author();
        newAuthor.setName(name);
        newAuthor.setSurname(surname);
        newAuthor.setPatronymic(patronymic);

        return authorRepository.save(newAuthor);
    }

    @Override
    @Transactional
    public void deleteById(BigDecimal id) {
        authorRepository.deleteById(id);
    }
}
