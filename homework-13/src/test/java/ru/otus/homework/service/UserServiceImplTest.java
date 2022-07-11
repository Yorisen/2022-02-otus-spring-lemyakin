package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.User;
import ru.otus.homework.repositories.GenreRepository;
import ru.otus.homework.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@DisplayName("Сервис пользователей должен")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    User firstUser;
    User secondUser;

    @BeforeEach
    public void setup() {
        firstUser = new User(BigDecimal.valueOf(1), "admin", "123456", "ADMIN");
        secondUser = new User(BigDecimal.valueOf(2), "user", "test", "USER");
    }

    @DisplayName("возвращать 2х пользователей")
    @Test
    void shouldReturn2Users() {
        List<User> expectedUsers = List.of(firstUser, secondUser);
        given(userRepository.findAll()).willReturn(expectedUsers);

        List<User> actualUsers = userService.findAll();
        assertThat(actualUsers).isEqualTo(expectedUsers);
    }
}