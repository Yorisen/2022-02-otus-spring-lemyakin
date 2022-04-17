package ru.otus.homework.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.domain.Author;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao{
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(BigDecimal id, String name, String surname, String patronymic) {
        namedParameterJdbcOperations.update("insert into authors " +
                        "(id, name, surname, patronymic) values (:id, :name, :surname, :patronymic)",
                Map.of("id", id, "name", name,"surname", surname, "patronymic", patronymic));
    }

    @Override
    public Author getById(BigDecimal id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name, surname, patronymic from authors where id = :id", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select id, name, surname, patronymic from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(BigDecimal id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from authors where id = :id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            BigDecimal id = resultSet.getBigDecimal("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String patronymic = resultSet.getString("patronymic");
            return new Author(id, name, surname, patronymic);
        }
    }
}
