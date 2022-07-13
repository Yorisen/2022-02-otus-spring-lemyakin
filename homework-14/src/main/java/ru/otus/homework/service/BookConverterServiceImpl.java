package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.domain.nosql.Author;
import ru.otus.homework.domain.nosql.Book;
import ru.otus.homework.domain.nosql.Comment;
import ru.otus.homework.domain.nosql.Genre;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookConverterServiceImpl implements BookConverterService {
    @Override
    public Book convert(ru.otus.homework.domain.sql.Book sqlBook) {
        return new Book(sqlBook.getId().toString(), sqlBook.getName(),
                convert(sqlBook.getAuthor()), convert(sqlBook.getGenre()),
                convert(sqlBook.getComments()));

    }

    private Author convert(ru.otus.homework.domain.sql.Author sqlAuthor) {
        return new Author(sqlAuthor.getId().toString(), getAuthorFullName(sqlAuthor));
    }

    private Genre convert(ru.otus.homework.domain.sql.Genre sqlGenre) {
        return new Genre(sqlGenre.getId().toString(), sqlGenre.getName());
    }

    private List<Comment> convert(List<ru.otus.homework.domain.sql.Comment> sqlComments) {
        List<Comment> comments = new ArrayList<>();
        for (ru.otus.homework.domain.sql.Comment sqlComment: sqlComments) {
            comments.add(convert(sqlComment));
        }

        return comments;
    }

    private Comment convert(ru.otus.homework.domain.sql.Comment sqlComment) {
        return new Comment(sqlComment.getId().toString(), sqlComment.getNickname(), sqlComment.getContent(),
                sqlComment.getCreationTimestamp());
    }

    private String getAuthorFullName(ru.otus.homework.domain.sql.Author author) {
        String patronymic = author.getPatronymic();

        return author.getSurname() + " " + author.getName() +
                ((patronymic != null && !patronymic.isBlank()) ? " " + patronymic : "");
    }
}
