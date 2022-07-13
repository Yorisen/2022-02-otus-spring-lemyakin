package ru.otus.homework.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework.domain.sql.Book;
import ru.otus.homework.service.BookConverterService;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class JobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final AppProps appProps;

    @StepScope
    @Bean
    public JpaPagingItemReader<Book> reader() {
        return new JpaPagingItemReaderBuilder<Book>()
                .entityManagerFactory(entityManagerFactory)
                .name("BookItemReader")
                .queryString("select b from Book b")
                .build();
    }

    @Bean
    public ItemProcessor<Book, ru.otus.homework.domain.nosql.Book> processor(BookConverterService bookConverterService) {
        return bookConverterService::convert;
    }

    @StepScope
    @Bean
    public MongoItemWriter<ru.otus.homework.domain.nosql.Book> writer(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<ru.otus.homework.domain.nosql.Book>()
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step transformBooksStep(ItemReader<Book> reader,
                                     MongoItemWriter<ru.otus.homework.domain.nosql.Book> writer,
                                     ItemProcessor<Book, ru.otus.homework.domain.nosql.Book> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<Book, ru.otus.homework.domain.nosql.Book>chunk(appProps.getChunkSize())
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importBooksJob(Step transformBooksStep) {
        return jobBuilderFactory.get("importBookJob")
                .incrementer(new RunIdIncrementer())
                .flow(transformBooksStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Book import starts...");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Book import completed");
                    }
                })
                .build();
    }
}
