package ru.otus.homework.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Comment;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.BookRepository;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class BooksInsertChangeLog {
    @ChangeSet(order = "001", id = "insertBooks", author = "LemyakinDV")
    public void insertBooks(BookRepository repository) {
        List<Book> booksForInsert = new ArrayList<>();

        List<Comment> commentsForBook = new ArrayList<>();
        commentsForBook.add(new Comment("1", "Nick", "Not bad!", getTimestamp("2022-05-03 00:44:59+03")));

        booksForInsert.add(new Book("1", "Harry Potter", new Author("Joanne Rowling"),
                new Genre("Fantasy"), commentsForBook));
        booksForInsert.add(new Book("2", "War and Peace", new Author("Lev Tolstoy Nikolayevich"),
                new Genre("Classic"), new ArrayList<>()));

        for (Book bookForInsert: booksForInsert) {
            repository.save(bookForInsert);
        }
    }

    private static Instant getTimestamp(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssx");
        ZonedDateTime dateTime = ZonedDateTime.parse(timestamp, formatter);
        return dateTime.toInstant();
    }
}
