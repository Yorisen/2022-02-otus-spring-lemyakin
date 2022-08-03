package ru.otus.homework.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.GenreRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @HystrixCommand(fallbackMethod="buildFallbackFindAll")
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @HystrixCommand(fallbackMethod="buildFallbackFind")
    @Override
    public Optional<Genre> getById(BigDecimal id) {
        return genreRepository.findById(id);
    }

    @HystrixCommand(fallbackMethod="buildFallbackInsert")
    @Override
    @Transactional
    public Genre insert(String name) {
        Genre newGenre = new Genre();
        newGenre.setName(name);

        return genreRepository.save(newGenre);
    }

    @HystrixCommand(fallbackMethod="buildFallbackDelete")
    @Override
    @Transactional
    public void deleteById(BigDecimal id) {
        genreRepository.deleteById(id);
    }

    public List<Genre> buildFallbackFindAll() {
        return new ArrayList<>();
    }

    public Optional<Genre> buildFallbackFind(BigDecimal id) {
        return Optional.ofNullable(new Genre());
    }

    public Genre buildFallbackInsert(String name) {
        return new Genre();
    }

    public void buildFallbackDelete(BigDecimal id) {
        //do nothing
    }
}
