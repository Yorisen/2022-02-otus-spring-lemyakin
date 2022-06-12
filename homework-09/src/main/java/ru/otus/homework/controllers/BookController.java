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
import ru.otus.homework.domain.Book;
import ru.otus.homework.service.BookService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/book/edit")
    public String viewBookPage(@RequestParam("id") BigDecimal id, Model model) throws ChangeSetPersister.NotFoundException {
        Book book = bookService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("/book/edit")
    public String saveBook(@ModelAttribute("book") Book book,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "book";
        }
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/book/add")
    public String addBookPage(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/book/delete")
    public String deletePage(@RequestParam("id") BigDecimal id, Model model) {
        bookService.deleteById(id);
        return "redirect:/";
    }
}
