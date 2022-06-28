package ru.otus.homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.domain.Book;
import ru.otus.homework.exceptions.NotFoundException;
import ru.otus.homework.repositories.BookRepository;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;

    @GetMapping("/books")
    public Flux<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Mono<Book> getBook(@PathVariable("id") String id) {
        return bookRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @DeleteMapping("/book/{id}")
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id);
    }

    @PostMapping("/book")
    public Mono<Book> createBook(@RequestBody Book book) {
        book.setId(null);
        return bookRepository.insert(book);
    }

    @PutMapping("/book")
    public Mono<Book> updateBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Not found!");
    }
}
