package ru.otus.homework.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Data
@Document(collection = "authors")
public class AuthorWithLinks {
    @Id
    private String id;
    private String name;
    private List<String> bookIds;
}
