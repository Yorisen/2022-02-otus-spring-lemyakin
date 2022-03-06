package ru.otus.homework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.reader.Reader;
import ru.otus.homework.reader.ReaderCSV;

@SuppressWarnings("unused")
@Configuration
public class ReadersConfig {

    @Bean
    public Reader reader(@Value("${file.name}") String fileName) {
        return new ReaderCSV(fileName);
    }
}
