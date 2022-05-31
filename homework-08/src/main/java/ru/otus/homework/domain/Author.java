package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {
    private String name;
}
