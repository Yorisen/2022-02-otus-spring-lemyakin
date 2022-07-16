package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {
    private final Job importBooksJob;
    private final JobLauncher jobLauncher;

    @ShellMethod(value = "Migration of books from SQL DB to NOSQL DB", key = {"migrate", "m"})
    public void migrationOfBooksFromSqlDbToNoSqlDb() throws Exception {
        JobExecution execution = jobLauncher.run(importBooksJob, new JobParametersBuilder().toJobParameters());
        System.out.println(execution);
    }
}
