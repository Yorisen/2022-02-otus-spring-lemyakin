package ru.otus.homework.domain;

import ru.otus.homework.domain.nosql.Author;
import ru.otus.homework.domain.nosql.Book;
import ru.otus.homework.domain.nosql.Genre;

import java.util.ArrayList;

public final class NoSqlBuilder {
    private static final String EXISTING_BOOK_ID = "1";
    private static final String EXISTING_BOOK_NAME = "Book2";
    private static final String EXISTING_BOOK_AUTHOR_ID = "2";
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Name2 Surname2 Patronymic2";
    private static final String EXISTING_BOOK_GENRE_ID = "2";
    private static final String EXISTING_BOOK_GENRE_NAME = "Genre2";

    public static Book buildExistingBook() {

        return new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, buildExistingAuthor(), buildExistingGenre(),
                new ArrayList<>());
    }

    public static Author buildExistingAuthor() {
        return new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME);
    }

    public static Genre buildExistingGenre() {
        return new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);
    }

}
