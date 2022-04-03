package ru.otus.homework.reader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ReaderCSV implements Reader{
    private final String fileName;

    public ReaderCSV(@Value("${file.locale}")String localeString,
                     MessageSource messageSource) {
        Locale locale = Locale.forLanguageTag(localeString);
        this.fileName = messageSource.getMessage("file.name", new String[] {}, locale);
    }

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
