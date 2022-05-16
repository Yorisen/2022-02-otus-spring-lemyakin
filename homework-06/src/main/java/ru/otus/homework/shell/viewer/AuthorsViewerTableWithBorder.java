package ru.otus.homework.shell.viewer;

import org.springframework.shell.table.BorderStyle;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Author;

import java.util.List;

@Component
public class AuthorsViewerTableWithBorder extends ViewerTableWithBorder implements AuthorsViewer {
    private static final String[] HEADERS = {"id", "name", "surname", "patronymic"};
    private static final int HEADER_ROWS_NUMBER = 1;
    private static final int COLUMNS_NUMBER = 4;
    private static final int TABLE_WIDTH = 80;
    private static final int ONE_ROW = 1;

    @Override
    public void view(List<Author> authors) {
        if (authors != null) {
            Object[][] table = new String[authors.size() + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];

            int rowNumber = 0;
            table[rowNumber] = HEADERS;

            for (Author author : authors) {
                rowNumber++;
                addAuthorToTable(table, rowNumber, author);
            }

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    @Override
    public void view(Author author) {
        if (author != null) {
            Object[][] table = new String[ONE_ROW + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];
            table[0] = HEADERS;
            addAuthorToTable(table, ONE_ROW, author);

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    void addAuthorToTable(Object[][] table, int rowNumber, Author author) {
        String[] columns = {author.getId().toString(),
                author.getName(), author.getSurname(), author.getPatronymic()};
        table[rowNumber] = columns;
    }
}
