package ru.otus.homework.shell.viewer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Book;

@Component
@RequiredArgsConstructor
public class BookWithCommentsViewerImpl implements BookWithCommentsViewer {
    private final BooksViewer booksViewer;
    private final CommentsViewer commentsViewer;

    @Override
    public void view(Book book) {
        booksViewer.view(book);
        System.out.println("Comments:");
        commentsViewer.view(book.getComments());
    }
}
