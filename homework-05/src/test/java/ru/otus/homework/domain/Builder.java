package ru.otus.homework.domain;

import java.math.BigDecimal;

public final class Builder {
    private static final BigDecimal EXISTING_BOOK_ID = BigDecimal.valueOf(1);
    private static final String EXISTING_BOOK_NAME = "Book2";
    private static final BigDecimal EXISTING_BOOK_AUTHOR_ID = BigDecimal.valueOf(2);
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Name2";
    private static final String EXISTING_BOOK_AUTHOR_SURNAME = "Surname2";
    private static final String EXISTING_BOOK_AUTHOR_PATRONYMIC = "Patronymic2";
    private static final BigDecimal EXISTING_BOOK_GENRE_ID = BigDecimal.valueOf(2);
    private static final String EXISTING_BOOK_GENRE_NAME = "Genre2";

    private static final BigDecimal EXISTING_GENRE_ID = BigDecimal.valueOf(1);
    private static final String EXISTING_GENRE_NAME = "Genre1";

    private static final BigDecimal EXISTING_AUTHOR_ID = BigDecimal.valueOf(1);
    private static final String EXISTING_AUTHOR_NAME = "Name1";
    private static final String EXISTING_AUTHOR_SURNAME = "Surname1";
    private static final String EXISTING_AUTHOR_PATRONYMIC = "Patronymic1";

    public static Book buildExistingBook() {
        return new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, buildExistingAuthor(), buildExistingGenre());
    }

    public static Author buildExistingAuthor() {
        return new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME,
                EXISTING_BOOK_AUTHOR_SURNAME, EXISTING_BOOK_AUTHOR_PATRONYMIC);
    }

    public static Author buildExistingAuthorWithoutLinkedBook() {
        return new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME,
                EXISTING_AUTHOR_SURNAME, EXISTING_AUTHOR_PATRONYMIC);
    }

    public static Genre buildExistingGenre() {
        return new Genre(EXISTING_BOOK_GENRE_ID, EXISTING_BOOK_GENRE_NAME);
    }

    public static Genre buildExistingGenreWithoutLinkedBook() {
        return new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
    }



}
