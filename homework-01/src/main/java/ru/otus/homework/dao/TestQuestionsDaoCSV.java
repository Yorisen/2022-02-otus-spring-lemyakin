package ru.otus.homework.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import ru.otus.homework.domain.TestQuestion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestQuestionsDaoCSV implements TestQuestionsDao {
    public static List<TestQuestion> testQuestions;

    public TestQuestionsDaoCSV(String fileName) {
        testQuestions = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()))) {
            String[] csvLine;
            while ((csvLine = csvReader.readNext()) != null) {
                testQuestions.add(getTestQuestionByCSVLine(csvLine));
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println(e.getMessage());
        }
    }

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

    public List<TestQuestion> get() {
        return testQuestions;
    }
}
