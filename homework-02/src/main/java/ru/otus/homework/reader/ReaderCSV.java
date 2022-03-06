package ru.otus.homework.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReaderCSV implements Reader{
    private final String fileName;

    @Override
    public List<String[]> readAllLines() {
        List<String[]> allLines = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new ClassPathResource(fileName).getInputStream()))) {
            String[] csvLine;
            while ((csvLine = csvReader.readNext()) != null) {
                allLines.add(csvLine);
            }
        } catch (IOException | CsvValidationException e) {
            System.out.println(e.getMessage());
        }

        return allLines;
    }
}
