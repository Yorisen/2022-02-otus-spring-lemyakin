package ru.otus.homework.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId) {
        namedParameterJdbcOperations.update("insert into books (id, name, author_id, genre_id) " +
                        "values (:id, :name, :author_id, :genre_id)",
                Map.of("id", bookId, "name", bookName,
                        "author_id", authorId, "genre_id", genreId));
    }

    @Override
    public Book getById(BigDecimal id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                """
                        select b.id, b.name,
                            b.author_id, a.name as "author_name", a.surname as "author_surname", a.patronymic as "author_patronymic",
                            b.genre_id, g.name as "genre_name" 
                        from books b 
                        left join authors a on b.author_id = a.id 
                        left join genres g on b.genre_id = g.id 
                        where b.id = :id""", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("""
                select b.id, b.name,
                    a.id as "author_id", a.name as "author_name", a.surname as "author_surname", a.patronymic as "author_patronymic",
                    g.id as "genre_id", g.name as "genre_name" 
                from books b 
                left join authors a on b.author_id = a.id 
                left join genres g on b.genre_id = g.id""", new BookMapper());
    }

    @Override
    public void deleteById(BigDecimal id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    @Override
    public void update(BigDecimal bookId, String bookName, BigDecimal authorId, BigDecimal genreId) {
        namedParameterJdbcOperations.update(
                "update books " +
                        "set name = :name, author_id = :author_id, genre_id = :genre_id " +
                        "where id = :id",
                Map.of("id", bookId, "name", bookName,
                        "author_id", authorId, "genre_id", genreId)
        );
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            BigDecimal id = resultSet.getBigDecimal("id");
            String name = resultSet.getString("name");
            BigDecimal authorId = resultSet.getBigDecimal("author_id");

            String authorName = resultSet.getString("author_name");
            String authorSurname = resultSet.getString("author_surname");
            String authorPatronymic = resultSet.getString("author_patronymic");
            Author author = new Author(authorId, authorName, authorSurname, authorPatronymic);

            BigDecimal genreId = resultSet.getBigDecimal("genre_id");
            String genreName = resultSet.getString("genre_name");
            Genre genre = new Genre(genreId, genreName);

            return new Book(id, name, author, genre);
        }
    }
}
