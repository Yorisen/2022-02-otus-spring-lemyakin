package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.domain.sql.Book;
import ru.otus.homework.service.NoSqlBookService;
import ru.otus.homework.service.SqlBookService;
import ru.otus.homework.shell.viewer.NoSqlBooksViewer;
import ru.otus.homework.shell.viewer.SqlBooksViewer;

import java.util.List;


@ShellComponent
@RequiredArgsConstructor
public class BookServiceCommands {
    private final SqlBookService sqlBookService;
    private final SqlBooksViewer sqlBooksViewer;
    private final NoSqlBookService noSqlBookService;
    private final NoSqlBooksViewer noSqlBooksViewer;


    @ShellMethod(value = "Get all books from SQL DB", key = {"books-sql", "bs"})
    public void getBooksSql() {
        List<Book> books = sqlBookService.findAll();
        sqlBooksViewer.view(books);
    }

    @ShellMethod(value = "Get all books from NOSQL DB", key = {"books-no-sql", "bns"})
    public void getBooksNoSql() {
        List<ru.otus.homework.domain.nosql.Book> books = noSqlBookService.findAll();
        noSqlBooksViewer.view(books);
    }

}
