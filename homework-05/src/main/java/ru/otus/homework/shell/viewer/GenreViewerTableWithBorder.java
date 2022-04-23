package ru.otus.homework.shell.viewer;

import org.springframework.shell.table.BorderStyle;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Genre;

import java.util.List;

@Component
public class GenreViewerTableWithBorder extends ViewerTableWithBorder implements GenreViewer {
    private static final String[] HEADERS = {"id", "name"};
    private static final int HEADER_ROWS_NUMBER = 1;
    private static final int COLUMNS_NUMBER = 2;
    private static final int TABLE_WIDTH = 80;
    private static final int ONE_ROW = 1;

    @Override
    public void view(List<Genre> genres) {
        if (genres != null) {
            Object[][] table = new String[genres.size() + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];

            int rowNumber = 0;
            table[rowNumber] = HEADERS;

            for (Genre genre: genres) {
                rowNumber++;
                addGenreToTable(table, rowNumber, genre);
            }

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    @Override
    public void view(Genre genre) {
        if (genre != null) {
            Object[][] table = new String[ONE_ROW + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];
            table[0] = HEADERS;
            addGenreToTable(table, ONE_ROW, genre);

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }

    void addGenreToTable(Object[][] table, int rowNumber, Genre genre) {
        String[] columns = {genre.getId().toString(), genre.getName()};
        table[rowNumber] = columns;
    }
}
