package ru.otus.homework.service;

import ru.otus.homework.domain.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
}
