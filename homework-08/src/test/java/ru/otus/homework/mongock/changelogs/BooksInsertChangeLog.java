package ru.otus.homework.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;
import ru.otus.homework.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class BooksInsertChangeLog {
    @ChangeSet(order = "001", id = "insertBooks", author = "LemyakinDV")
    public void insertBooks(BookRepository repository) {
        List<Book> booksForInsert = new ArrayList<>();

        booksForInsert.add(new Book("1", "Book2", new Author("Author2"),
                new Genre("Genre2"), new ArrayList<>()));

        for (Book bookForInsert: booksForInsert) {
            repository.save(bookForInsert);
        }
    }
}
