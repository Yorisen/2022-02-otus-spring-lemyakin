package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.GenreRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getById(BigDecimal id) {
        return genreRepository.findById(id);
    }

    @Override
    @Transactional
    public Genre insert(String name) {
        Genre newGenre = new Genre();
        newGenre.setName(name);

        return genreRepository.save(newGenre);
    }

    @Override
    @Transactional
    public void deleteById(BigDecimal id) {
        genreRepository.deleteById(id);
    }
}
