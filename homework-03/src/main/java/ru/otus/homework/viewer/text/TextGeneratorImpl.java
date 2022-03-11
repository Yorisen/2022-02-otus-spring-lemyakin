package ru.otus.homework.viewer.text;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homework.domain.Student;

import java.util.List;
import java.util.Locale;

@Component
public class TextGeneratorImpl implements TextGenerator{
    private final MessageSource messageSource;
    private final Locale locale;


    public TextGeneratorImpl(MessageSource messageSource, @Value("${file.locale}")String locale) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(locale);
    }

    @Override
    public String getIntroduceYourself() {
        return getMessage("text.introduce-yourself");
    }

    @Override
    public String getNameRequest() {
        return getMessage("text.name-request");
    }

    @Override
    public String getSurnameRequest() {
        return getMessage("text.surname-request");
    }

    @Override
    public String getTestStart() {
        return getMessage("text.test-start");
    }

    @Override
    public String getTestEnd() {
        return getMessage("text.test-end") + "\n";
    }

    @Override
    public String getTestQuestion(String testQuestion, List<String> answerOptions,
                                  int numberOfAnswerOptions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMessage("text.question")).append(" ").append(testQuestion).append("\n")
                .append(getMessage("text.answer-options")).append("\n");

        for (int answerNumber = 0; numberOfAnswerOptions > answerNumber; answerNumber++) {
            stringBuilder.append(answerNumber).append(" - ").append(answerOptions.get(answerNumber)).append("\n");
        }

        return stringBuilder.toString();
    }

    @Override
    public String getAnswerRequest(int numberOfAnswerOptions) {
        return getMessage("text.answer-request",
                new String[] {toString(numberOfAnswerOptions - 1)});
    }

    @Override
    public String getResults(Student student, int numberOfQuestions, int numberOfCorrectAnswers, boolean isTestPassed) {
        return getMessage("text.results",
                new String[] {student.getName(), student.getSurname(),
                        toString(numberOfCorrectAnswers),
                        toString(numberOfQuestions),
                        ((isTestPassed) ?
                                getMessage("text.passed") :
                                getMessage("text.did-not-pass"))});
    }

    @Override
    public String getInvalidPassingScore(int numberOfTestQuestions, int testPassingScore) {
        return getMessage("text.invalid-passing-score",
                new String[] {toString(testPassingScore), toString(numberOfTestQuestions)});
    }

    private String getMessage(String messageProperty) {
        return messageSource.getMessage(messageProperty, new String[]{}, locale);
    }

    private String getMessage(String messageProperty, String[] variables) {
        return messageSource.getMessage(messageProperty, variables, locale);
    }

    private String toString(int number) {
        return Integer.toString(number);
    }
}
