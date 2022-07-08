package ru.otus.homework.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Builder;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.BookService;
import ru.otus.homework.service.GenreService;
import ru.otus.homework.service.UserService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@DisplayName("Контроллер книг должен")
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private UserService userService;

    private static final BigDecimal BOOK_ID = BigDecimal.valueOf(1);

    Book book;
    Optional<Book> bookOptional;

    @BeforeEach
    public void setup() {
        book = Builder.buildExistingBook();
        book.setId(BOOK_ID);
        bookOptional = Optional.ofNullable(book);
    }

    @DisplayName("возвращать 200 HTTP статус при получении всех книг пользователем user")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldReturnHttpStatus200onRequestOfAllBooksFromUserUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @DisplayName("возвращать 200 HTTP статус при получении книги пользователем user")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldReturnHttpStatus200OnBookRequestFromUserUser() throws Exception {
        given(bookService.findById(BOOK_ID)).willReturn(bookOptional);

        mockMvc.perform(get("/book/edit?id=" + BOOK_ID))
                .andExpect(status().isOk());
    }

    @DisplayName("возвращать 3xx HTTP статус при обновлении книги пользователем user")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldReturnHttpStatus3xxOnUpdateBookByUserUser() throws Exception {
        given(bookService.update(book.getId(),
                book.getName(),
                book.getAuthor().getId(),
                book.getGenre().getId())).willReturn(book);

        mockMvc.perform(post("/book/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("id=" + book.getId() +
                                "&name=" + book.getName() +
                                "&author.id=" + book.getAuthor().getId() +
                                "&genre.id=" + book.getGenre().getId()))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("возвращать 3xx HTTP статус при добавлении книги пользователем user")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldReturnHttpStatus3xxOnAddingBookByUserUser() throws Exception {
        given(bookService.insert(book.getName(),
                book.getAuthor().getId(),
                book.getGenre().getId())).willReturn(book);

        mockMvc.perform(post("/book/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("name=" + book.getName() +
                                "&author.id=" + book.getAuthor().getId() +
                                "&genre.id=" + book.getGenre().getId()))
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("возвращать 200 HTTP статус при запросе формы добавления книги пользователем user")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void shouldReturnHttpStatus200OnRequestOfAddingBookFormFromUserUser() throws Exception {
        mockMvc.perform(get("/book/add"))
            .andExpect(status().isOk());
    }

    @DisplayName("возвращать 3xx HTTP статус при удалении книги пользователем user")
    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    void deletePage() throws Exception {
        mockMvc.perform(get("/book/delete?id=" + BOOK_ID))
                .andExpect(status().is3xxRedirection());
    }
}