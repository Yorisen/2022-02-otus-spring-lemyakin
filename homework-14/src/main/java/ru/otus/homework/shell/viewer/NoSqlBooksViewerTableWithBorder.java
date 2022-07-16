package ru.otus.homework.shell.viewer;

import org.springframework.shell.table.BorderStyle;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.nosql.Book;

import java.util.List;


@Component
public class NoSqlBooksViewerTableWithBorder extends ViewerTableWithBorder implements NoSqlBooksViewer {
    private static final String[] HEADERS = {"id", "name", "author", "genre"};
    private static final int HEADER_ROWS_NUMBER = 1;
    private static final int COLUMNS_NUMBER = 4;
    private static final int TABLE_WIDTH = 80;

    @Override
    public void view(List<Book> books) {
        if (books != null) {
            Object[][] table = new String[books.size() + HEADER_ROWS_NUMBER][COLUMNS_NUMBER];

            int rowNumber = 0;
            table[rowNumber] = HEADERS;

            for (Book book : books) {
                rowNumber++;
                addBookToTable(table, rowNumber, book);
            }

            viewTable(table, BorderStyle.fancy_light, TABLE_WIDTH);
        } else {
            viewNoDataToShow();
        }
    }


    private void addBookToTable(Object[][] table, int rowNumber, Book book) {
        String[] columns = {book.getId(),
                book.getName(), book.getAuthor().getName(), book.getGenre().getName()};
        table[rowNumber] = columns;
    }


}
