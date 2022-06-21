package ru.otus.homework.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private BigDecimal id;
    private String name;
    private BigDecimal authorId;
    private BigDecimal genreId;
}
