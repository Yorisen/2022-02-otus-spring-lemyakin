package ru.otus.homework.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestQuestionTest {

    @Test
    void testToString() {
        String expected = "Question: Question\n" +
                "Answer options:\n" +
                "0 - Answer1\n" +
                "1 - Answer2\n" +
                "2 - Answer3\n" +
                "3 - Answer4\n";

        TestQuestion testQuestion = TestQuestionBuilder.build();

        assertEquals(expected, testQuestion.toString(), "Strings should be equal");
    }
}