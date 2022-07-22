package ru.otus.homework.actuators;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.homework.service.AuthorService;
import ru.otus.homework.service.GenreService;

@RequiredArgsConstructor
@Component
public class DbDataHealthIndicator implements HealthIndicator {
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Health health() {
        boolean containsAuthors = !authorService.findAll().isEmpty();
        boolean containsGenres = !genreService.findAll().isEmpty();

        Health health;

        if (!containsAuthors && !containsGenres) {
            health = Health
                    .down()
                    .status(Status.DOWN)
                    .withDetail("details", "DB not contains any author and genre")
                    .build();
        } else if (!containsAuthors) {
            health = Health
                    .down()
                    .status(Status.DOWN)
                    .withDetail("details", "DB not contains any author")
                    .build();
        } else if (!containsGenres) {
            health = Health
                    .down()
                    .status(Status.DOWN)
                    .withDetail("details", "DB not contains any genre")
                    .build();
        } else {
            health = Health
                    .up()
                    .withDetail("details", "DB contains authors and genres")
                    .build();
        }

        return health;
    }
}
