package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsViewer {
    void view(List<Comment> comment);
    void view(Optional<Comment> comment);
}
