package ru.otus.homework.domain.nosql;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String name;
    private Author author;
    private Genre genre;
    private List<Comment> comments;
}
