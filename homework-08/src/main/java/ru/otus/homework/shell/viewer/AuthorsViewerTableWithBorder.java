package ru.otus.homework.shell.viewer;

import org.springframework.shell.table.BorderStyle;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.AuthorWithLinks;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorsViewerTableWithBorder extends ViewerTableWithBorder implements AuthorsViewer {
    private static final String[] HEADERS = {"id", "name"};
    private static final int HEADER_ROWS_NUMBER = 1;
    private static final int COLUMNS_NUMBER = 2;
    private static final int TABLE_WIDTH = 80;
    private static final int ONE_ROW = 1;

    @Override
    public void view(List<AuthorWithLinks> authors) {
        if (authors != null) {
            Object[][] table = new String[authors.size() + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];

            int rowNumber = 0;
            table[rowNumber] = HEADERS;

            for (AuthorWithLinks author : authors) {
                rowNumber++;
                addAuthorToTable(table, rowNumber, author);
            }

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    @Override
    public void view(Optional<AuthorWithLinks> author) {
        if (author.isPresent()) {
            Object[][] table = new String[ONE_ROW + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];
            table[0] = HEADERS;
            addAuthorToTable(table, ONE_ROW, author.get());

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    void addAuthorToTable(Object[][] table, int rowNumber, AuthorWithLinks author) {
        String[] columns = {author.getId(), author.getName()};
        table[rowNumber] = columns;
    }
}
