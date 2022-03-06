package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.TestQuestionsDao;
import ru.otus.homework.dao.TestQuestionsDaoCSV;
import ru.otus.homework.reader.Reader;

@SuppressWarnings("unused")
@Configuration
public class DaoConfig {

    @Bean
    public TestQuestionsDao testQuestionsDao(Reader reader) {
        return new TestQuestionsDaoCSV(reader);
    }
}
