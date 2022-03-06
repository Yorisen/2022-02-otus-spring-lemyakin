package ru.otus.homework.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.reader.Reader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TestQuestionsDaoCSV implements TestQuestionsDao {
    private final Reader reader;

    public static TestQuestion getTestQuestionByCSVLine(String[] csvLine) {
        String question = csvLine[0];
        String correctAnswer = csvLine[1];
        List<String> answerOptions = new ArrayList<>();
        for (int linePartNumber = 2, numberOfLineParts = csvLine.length;
             numberOfLineParts > linePartNumber;
             linePartNumber++) {

            String answerOption = csvLine[linePartNumber];
            answerOptions.add(answerOption);
        }

        return new TestQuestion(question, correctAnswer, answerOptions);
    }

    @Override
    public List<TestQuestion> get() {
        List<String[]> allCSVLines = reader.readAllLines();
        List<TestQuestion> testQuestions = new ArrayList<>();

        for (String[] csvLine: allCSVLines) {
            testQuestions.add(getTestQuestionByCSVLine(csvLine));
        }

        return testQuestions;
    }
}
