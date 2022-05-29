package ru.otus.homework.shell.viewer;

import org.springframework.shell.table.BorderStyle;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Comment;

import java.util.List;
import java.util.Optional;

@Component
public class CommentsViewerTableWithBorder extends ViewerTableWithBorder implements CommentsViewer {
    private static final String[] HEADERS = {"id", "nickname", "content", "creation_timestamp", "book_id"};
    private static final int HEADER_ROWS_NUMBER = 1;
    private static final int COLUMNS_NUMBER = 5;
    private static final int TABLE_WIDTH = 80;
    private static final int ONE_ROW = 1;

    @Override
    public void view(List<Comment> comments) {
        if (comments != null) {
            Object[][] table = new String[comments.size() + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];

            int rowNumber = 0;
            table[rowNumber] = HEADERS;

            for (Comment comment : comments) {
                rowNumber++;
                addCommentToTable(table, rowNumber, comment);
            }

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    @Override
    public void view(Optional<Comment> comment) {
        if (comment.isPresent()) {
            Object[][] table = new String[ONE_ROW + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];
            table[0] = HEADERS;
            addCommentToTable(table, ONE_ROW, comment.get());

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    void addCommentToTable(Object[][] table, int rowNumber, Comment comment) {
        String[] columns = {comment.getId().toString(), comment.getNickname(), comment.getContent(),
                comment.getCreationTimestamp().toString(), comment.getBook().getId().toString()};
        table[rowNumber] = columns;
    }
}
