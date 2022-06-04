package ru.otus.homework.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import ru.otus.homework.domain.*;
import ru.otus.homework.repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class AuthorsWithLinksInsertChangeLog {
    @ChangeSet(order = "001", id = "insertAuthorsWithLinks", author = "LemyakinDV")
    public void insertBooks(AuthorRepository repository) {
        List<AuthorWithLinks> authorsForInsert = new ArrayList<>();

        authorsForInsert.add(new AuthorWithLinks("1", "Joanne Rowling", List.of("1")));
        authorsForInsert.add(new AuthorWithLinks("2", "Lev Tolstoy Nikolayevich", List.of("2")));

        for (AuthorWithLinks authorForInsert: authorsForInsert) {
            repository.save(authorForInsert);
        }
    }
}
