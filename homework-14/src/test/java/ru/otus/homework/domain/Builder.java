package ru.otus.homework.domain;

import ru.otus.homework.domain.sql.Author;
import ru.otus.homework.domain.sql.Book;
import ru.otus.homework.domain.sql.Genre;

import java.math.BigDecimal;
import java.util.ArrayList;

public final class Builder {
    private static final BigDecimal EXISTING_BOOK_ID = BigDecimal.valueOf(1);
    private static final String EXISTING_BOOK_NAME = "Book2";
    private static final BigDecimal EXISTING_BOOK_AUTHOR_ID = BigDecimal.valueOf(2);
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Name2";
    private static final String EXISTING_BOOK_AUTHOR_SURNAME = "Surname2";
    private static final String EXISTING_BOOK_AUTHOR_PATRONYMIC = "Patronymic2";
    private static final BigDecimal EXISTING_BOOK_GENRE_ID = BigDecimal.valueOf(2);
    private static final String EXISTING_BOOK_GENRE_NAME = "Genre2";

    public static Book buildExistingBook() {

        return new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, buildExistingAuthor(), buildExistingGenre(),
                new ArrayList<>());
    }

    public static Author buildExistingAuthor() {
        return new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME,
                EXISTING_BOOK_AUTHOR_SURNAME, EXISTING_BOOK_AUTHOR_PATRONYMIC);
    }

    public static Genre buildExistingGenre() {
        return new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);
    }

}
