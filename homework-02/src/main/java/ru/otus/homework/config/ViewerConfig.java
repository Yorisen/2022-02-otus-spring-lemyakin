package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.viewer.Viewer;
import ru.otus.homework.viewer.ViewerCmd;
import ru.otus.homework.viewer.text.TextGenerator;

@SuppressWarnings("unused")
@Configuration
public class ViewerConfig {
    @Bean
    public Viewer viewer(TextGenerator textGenerator) {
        return new ViewerCmd(textGenerator);
    }
}
