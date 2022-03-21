package ru.otus.homework.dao;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.domain.TestQuestionBuilder;
import ru.otus.homework.reader.Reader;

import static org.junit.jupiter.api.Assertions.*;

class TestQuestionsDaoCSVTest {
    @Mock private Reader reader;

    @Test
    void getTestQuestionByCSVLine() {
        TestQuestion expectedTestQuestion = TestQuestionBuilder.build();

        String csvLineString = "Question,Answer2,Answer1,Answer2,Answer3,Answer4";
        String [] csvLine = csvLineString.split(",");
        TestQuestionsDaoCSV testQuestionsDaoCSV = new TestQuestionsDaoCSV(reader);
        TestQuestion testQuestionFromCSVLine = testQuestionsDaoCSV.getTestQuestionByCSVLine(csvLine);

        assertEquals(expectedTestQuestion, testQuestionFromCSVLine, "Objects not equal");
    }
}