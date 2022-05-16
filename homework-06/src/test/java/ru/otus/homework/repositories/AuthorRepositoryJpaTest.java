package ru.otus.homework.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Builder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий авторов должен")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepositoryJpa authorRepository;

    private static final int EXPECTED_AUTHORS_COUNT = 3;

    @DisplayName("добавлять автора")
    @Test
    void shouldInsertAuthor() {
        Author authorForInsert = Builder.getNewAuthor("SomeName", "SomeSurname", null);

        authorRepository.insert(authorForInsert);
        Author insertedAuthor = authorRepository.findById(authorForInsert.getId());
        assertThat(insertedAuthor).isNotNull();
    }

    @DisplayName("получать автора по существующему id")
    @Test
    void shouldGetAuthorById() {
        Author expectedAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        Author actualAuthor = authorRepository.findById(expectedAuthor.getId());
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("получать всех авторов")
    @Test
    void shouldGetAllAuthors() {
        Author expectedAuthor = Builder.buildExistingAuthorWithoutLinkedBook();
        List<Author> actualAuthors = authorRepository.findAll();
        assertThat(actualAuthors.size()).isEqualTo(EXPECTED_AUTHORS_COUNT);
        assertThat(actualAuthors).containsAnyOf(expectedAuthor);
    }

    @DisplayName("удалять автора по id")
    @Test
    void shouldDeleteAuthorById() {
        Author authorForInsert = Builder.getNewAuthor("SomeName", "SomeSurname", null);

        authorRepository.insert(authorForInsert);
        Author insertedAuthor = authorRepository.findById(authorForInsert.getId());
        assertThat(insertedAuthor).isNotNull();

        authorRepository.deleteById(insertedAuthor.getId());
        Author deletedAuthor = authorRepository.findById(authorForInsert.getId());
        assertThat(deletedAuthor).isNull();
    }
}