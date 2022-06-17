package ru.otus.homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.dtos.BookDto;
import ru.otus.homework.exceptions.NotFoundException;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public ModelAndView getIndexPage() {
        return new ModelAndView("index");
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        List<Book> books = bookService.findAll();
        for (Book book: books) {
            book.setComments(new ArrayList<>());
        }
        return bookService.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") BigDecimal id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            book.get().setComments(new ArrayList<>());
        }
        return book.orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("/book/{id}")
    public Book deleteBook(@PathVariable("id") BigDecimal id) {
        bookService.deleteById(id);
        return null;
    }

    @PostMapping("/book")
    public Book createBook(@RequestBody BookDto book) {
        return bookService.insert(book.getName(), book.getAuthorId(), book.getGenreId());
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody BookDto book) {
        return bookService.update(book.getId(), book.getName(), book.getAuthorId(), book.getGenreId());
    }

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return genreService.findAll();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Not found!");
    }
}
