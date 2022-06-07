package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.AuthorWithLinks;
import ru.otus.homework.domain.Book;
import ru.otus.homework.repositories.AuthorRepository;
import ru.otus.homework.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<AuthorWithLinks> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<AuthorWithLinks> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public AuthorWithLinks insert(String name) {
        return authorRepository.insert(new AuthorWithLinks(null, name, new ArrayList<>()));
    }

    @Override
    public AuthorWithLinks update(String id, String name) {
        Optional<AuthorWithLinks> authorOptional = authorRepository.findById(id);
        AuthorWithLinks foundAuthor = null;
        if (authorOptional.isPresent()) {
            foundAuthor = authorOptional.get();
            foundAuthor.setName(name);
            List<String> bookIds = foundAuthor.getBookIds();

            authorRepository.save(foundAuthor);

            for (String bookId: bookIds) {
                Optional<Book> bookOptional = bookRepository.findById(bookId);
                if (bookOptional.isPresent()) {
                    Book foundBook = bookOptional.get();
                    foundBook.setAuthor(new Author(foundAuthor.getId(), foundAuthor.getName()));

                    bookRepository.save(foundBook);
                }
            }
        }
        return foundAuthor;
    }

}
