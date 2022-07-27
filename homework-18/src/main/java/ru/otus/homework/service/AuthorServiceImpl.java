package ru.otus.homework.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Author;
import ru.otus.homework.repositories.AuthorRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @HystrixCommand(fallbackMethod="buildFallbackFindAll")
    @Override
    public List<Author> findAll() {
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        return authorRepository.findAll();
    }

    @HystrixCommand(fallbackMethod="buildFallbackFind")
    @Override
    public Optional<Author> findById(BigDecimal id) {
        return authorRepository.findById(id);
    }

    @HystrixCommand(fallbackMethod="buildFallbackInsert")
    @Override
    @Transactional
    public Author insert(String name, String surname, String patronymic) {
        Author newAuthor = new Author();
        newAuthor.setName(name);
        newAuthor.setSurname(surname);
        newAuthor.setPatronymic(patronymic);

        return authorRepository.save(newAuthor);
    }

    @HystrixCommand(fallbackMethod="buildFallbackDelete")
    @Override
    @Transactional
    public void deleteById(BigDecimal id) {
        authorRepository.deleteById(id);
    }

    public List<Author> buildFallbackFindAll() {
        return new ArrayList<>();
    }

    public Optional<Author> buildFallbackFind(BigDecimal id) {
        return Optional.ofNullable(new Author());
    }

    public Author buildFallbackInsert(String name, String surname, String patronymic) {
        return new Author();
    }

    public void buildFallbackDelete(BigDecimal id) {
        //do nothing
    }
}
