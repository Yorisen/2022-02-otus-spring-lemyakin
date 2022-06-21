package ru.otus.homework.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
    private static final BigDecimal EXISTING_COMMENT_ID = BigDecimal.valueOf(1);
    private static final String EXISTING_COMMENT_NICKNAME = "nickname1";
    private static final String EXISTING_COMMENT_CONTENT = "content1";
    private static final String EXISTING_COMMENT_CREATION_DATE = "2022-05-03 00:44:59+03";

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


    public static Comment buildExistingComment() {
        Instant creationTimestamp = getTimestamp(EXISTING_COMMENT_CREATION_DATE);
        Book existingBook = buildExistingBook();
        existingBook.getComments().add(new Comment(EXISTING_COMMENT_ID, EXISTING_COMMENT_NICKNAME, EXISTING_COMMENT_CONTENT,
                creationTimestamp, existingBook));
        existingBook.getComments().get(0).setBook(existingBook);
        return existingBook.getComments().get(0);
    }

    private static Instant getTimestamp(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx");
        ZonedDateTime dateTime = ZonedDateTime.parse(timestamp, formatter);
        return dateTime.toInstant();
    }

}
