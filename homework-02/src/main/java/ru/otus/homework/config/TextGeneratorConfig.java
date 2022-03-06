package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.viewer.text.TextGenerator;
import ru.otus.homework.viewer.text.TextGeneratorEn;

@SuppressWarnings("unused")
@Configuration
public class TextGeneratorConfig {
    @Bean
    public TextGenerator textGenerator() {
        return new TextGeneratorEn();
    }
}
