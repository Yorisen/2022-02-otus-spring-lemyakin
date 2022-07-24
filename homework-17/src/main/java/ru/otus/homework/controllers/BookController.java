package ru.otus.homework.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/book/edit")
    public String viewBookPage(@RequestParam("id") BigDecimal id, Model model) throws ChangeSetPersister.NotFoundException {
        Book book = bookService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "book";
    }

    @PostMapping("/book/edit")
    public String saveBook(@ModelAttribute("book") Book book,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "book";
        }
        if (book.getId() != null) {
            bookService.update(book.getId(), book.getName(), book.getAuthor().getId(), book.getGenre().getId());
        } else {
            bookService.insert(book.getName(), book.getAuthor().getId(), book.getGenre().getId());
        }
        return "redirect:/";
    }

    @GetMapping("/book/add")
    public String addBookPage(Model model) {
        Book book = new Book();
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "book";
    }

    @GetMapping("/book/delete")
    public String deletePage(@RequestParam("id") BigDecimal id, Model model) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
