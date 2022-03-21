package ru.otus.homework.reader;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReaderCSVTest {

    @Test
    void readAllLines() {
        List<String[]> expectedList = new ArrayList<>();
        String[] expectedLine = {"Question","Answer2","Answer1","Answer2","Answer3","Answer4"};
        expectedList.add(expectedLine);

        ReaderCSV readerCSV = new ReaderCSV("questions.csv");
        List<String[]> resultList = readerCSV.readAllLines();

        assertArrayEquals(expectedList.get(0), resultList.get(0), "Arrays should be equal");

    }
}