package ru.otus.homework.shell.viewer;

import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

public class ViewerTableWithBorder {
    public void viewTable(Object[][] tableData, BorderStyle style, int totalAvailableWidth) {
        TableModel model = new ArrayTableModel(tableData);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addFullBorder(style);

        System.out.println(tableBuilder.build().render(totalAvailableWidth));
    }

    public void viewNoDataToShow() {
        System.out.println("No data to show");
    }
}
