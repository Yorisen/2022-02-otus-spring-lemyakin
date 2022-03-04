package ru.otus.homework.dao;

import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.domain.TestQuestionBuilder;

import static org.junit.jupiter.api.Assertions.*;

class TestQuestionsDaoCSVTest {

    @Test
    void getTestQuestionByCSVLine() {
        TestQuestion expectedTestQuestion = TestQuestionBuilder.build();

        String csvLineString = "Question,Answer2,Answer1,Answer2,Answer3,Answer4";
        String [] csvLine = csvLineString.split(",");
        TestQuestion testQuestionFromCSVLine = TestQuestionsDaoCSV.getTestQuestionByCSVLine(csvLine);

        assertEquals(expectedTestQuestion, testQuestionFromCSVLine, "Objects not equal");
    }
}