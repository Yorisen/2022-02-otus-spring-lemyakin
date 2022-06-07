package ru.otus.homework.domain;

import java.util.ArrayList;
import java.util.List;

public final class Builder {
    private static final String EXISTING_BOOK_ID = "1";
    private static final String EXISTING_BOOK_NAME = "Book2";
    private static final String EXISTING_BOOK_AUTHOR_ID = "2";
    private static final String EXISTING_BOOK_AUTHOR_NAME = "Name2";
    private static final String EXISTING_BOOK_GENRE_NAME = "Genre2";

    public static Book buildExistingBook() {

        return new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, buildExistingAuthor(), buildExistingGenre(),
                new ArrayList<>());
    }

    public static Author buildExistingAuthor() {
        return new Author(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME);
    }

    public static AuthorWithLinks buildExistingAuthorWithLinks() {
        return new AuthorWithLinks(EXISTING_BOOK_AUTHOR_ID, EXISTING_BOOK_AUTHOR_NAME, List.of(EXISTING_BOOK_ID));
    }

    public static Genre buildExistingGenre() {
        return new Genre(EXISTING_BOOK_GENRE_NAME);
    }

}
