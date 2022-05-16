package ru.otus.homework.shell.viewer;

import ru.otus.homework.domain.Comment;

import java.util.List;

public interface CommentsViewer {
    void view(List<Comment> comment);
    void view(Comment comment);
}
