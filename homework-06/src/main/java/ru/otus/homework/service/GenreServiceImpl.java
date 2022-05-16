package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.GenreRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getById(BigDecimal id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre insert(String name) {
        Genre newGenre = new Genre();
        newGenre.setName(name);

        return genreRepository.insert(newGenre);
    }

    @Override
    public void deleteById(BigDecimal id) {
        genreRepository.deleteById(id);
    }
}
