package ru.otus.homework.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Comment> buildExistingCommentsList() {
        Book existingBook = buildExistingBook();
        existingBook.getComments().add(buildExistingComment());
        existingBook.getComments().get(0).setBook(existingBook);

        return existingBook.getComments();
    }

    public static Comment buildExistingComment() {
        Instant creationTimestamp = getTimestamp(EXISTING_COMMENT_CREATION_DATE);
        Book existingBook = buildExistingBook();
        existingBook.getComments().add(new Comment(EXISTING_COMMENT_ID, EXISTING_COMMENT_NICKNAME, EXISTING_COMMENT_CONTENT,
                creationTimestamp, existingBook));
        existingBook.getComments().get(0).setBook(existingBook);
        return existingBook.getComments().get(0);
    }

    public static Book getNewBookWithoutComments(String name, String genre, String authorName, String authorSurname,
                                  String authorPatronymic) {

        Book newBook = new Book();
        newBook.setName(name);
        newBook.setGenre(getNewGenre(genre));
        newBook.setAuthor(getNewAuthor(authorName, authorSurname, authorPatronymic));

        return newBook;
    }

    public static Author getNewAuthor(String name, String surname,
                                      String patronymic) {
        Author newAuthor = new Author();
        newAuthor.setName(name);
        newAuthor.setSurname(surname);
        newAuthor.setPatronymic(patronymic);

        return newAuthor;
    }

    public static Genre getNewGenre(String name) {
        Genre newGenre = new Genre();
        newGenre.setName(name);

        return newGenre;
    }

    private static Instant getTimestamp(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx");
        ZonedDateTime dateTime = ZonedDateTime.parse(timestamp, formatter);
        return dateTime.toInstant();
    }

}
