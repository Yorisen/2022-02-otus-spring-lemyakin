package ru.otus.homework.configs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework.service.NoSqlBookServiceImpl;
import ru.otus.homework.service.SqlBookServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
@DisplayName("Job для импорта книг из SQL БД в NOSQL БД должен")
class ImportBooksJobTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private NoSqlBookServiceImpl noSqlBookService;

    @Autowired
    private SqlBookServiceImpl sqlBookService;

    @BeforeEach
    void clearMetaData() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @Test
    @DisplayName("иметь одинаковое число записей в SQL и NOSQL базах данных")
    void haveTheSameNumberOfRecordsInSqlAndNoSqlDbs() throws Exception {
        assertThat(noSqlBookService.findAll().size()).isEqualTo(0);

        Job job = jobLauncherTestUtils.getJob();
        assertThat(job).isNotNull()
                .extracting(Job::getName)
                .isEqualTo("importBookJob");

        JobParameters parameters = new JobParametersBuilder().toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(parameters);

        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");

        assertThat(sqlBookService.findAll().size()).isEqualTo(noSqlBookService.findAll().size());
    }
}