package ru.otus.homework.reader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
class ReaderCSVEnTest {

    @ComponentScan({"ru.otus.homework.reader",
            "ru.otus.homework.dao"})
    @Configuration
    static class ReaderCSVEnTestConfiguration {
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource source = new ResourceBundleMessageSource();
            source.setDefaultEncoding("UTF-8");
            source.setBasenames("i18n/messages");
            return source;
        }
    }

    @Autowired
    Reader reader;

    @Test
    void readAllLines() {
        List<String[]> expectedList = new ArrayList<>();
        String[] expectedLine = {"Question","Answer2","Answer1","Answer2","Answer3","Answer4"};
        expectedList.add(expectedLine);

        List<String[]> resultList = reader.readAllLines();

        assertArrayEquals(expectedList.get(0), resultList.get(0), "Arrays should be equal");

    }
}