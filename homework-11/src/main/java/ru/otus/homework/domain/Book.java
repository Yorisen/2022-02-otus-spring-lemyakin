package ru.otus.homework.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String author;
    private String genre;
}
