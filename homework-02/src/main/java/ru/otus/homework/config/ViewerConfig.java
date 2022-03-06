package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.viewer.Viewer;

@Configuration
public class ViewerConfig {
    @Bean
    public Viewer viewer() {
        return new Viewer();
    }
}
