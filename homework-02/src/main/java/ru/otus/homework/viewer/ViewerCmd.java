package ru.otus.homework.viewer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.TestQuestion;
import ru.otus.homework.viewer.text.TextGenerator;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ViewerCmd implements Viewer  {
    private Scanner scanner = null;
    private final TextGenerator textGenerator;

    @Override
    public Student showIntroduceYourself() {
        print(textGenerator.getIntroduceYourself());
        String name = showRequest(textGenerator.getNameRequest());
        String surname = showRequest(textGenerator.getSurnameRequest());

        return new Student(name, surname);
    }

    @Override
    public void showTheTestHasBegun() {
        print(textGenerator.getTestStart());
    }

    @Override
    public void showTheTestIsOver() {
        print(textGenerator.getTestEnd());
    }

    @Override
    public String showTestQuestion(TestQuestion testQuestion) {
        List<String> answerOptions = testQuestion.getAnswerOptions();
        int numberOfAnswerOptions = answerOptions.size();

        print(textGenerator.getTestQuestion(testQuestion.getQuestion(), answerOptions,
                numberOfAnswerOptions));

        boolean responseIsNumber = false;
        int responseNumber = -1;

        while (!responseIsNumber || (responseNumber < 0) || (responseNumber > numberOfAnswerOptions)) {
            String response = showRequest(textGenerator.getAnswerRequest(numberOfAnswerOptions));
            responseIsNumber = response.matches("\\d+");
            if (responseIsNumber) {
                responseNumber = Integer.parseInt(response);
            }
        }

        return answerOptions.get(responseNumber);
    }

    @Override
    public void showResults(Student student, int numberOfQuestions, int numberOfCorrectAnswers, boolean isTestPassed) {
        print(textGenerator.getResults(student, numberOfQuestions, numberOfCorrectAnswers, isTestPassed));
        read();
    }

    @Override
    public void showInvalidPassingScore(int numberOfTestQuestions, int testPassingScore) {
        print(textGenerator.getInvalidPassingScore(numberOfTestQuestions, testPassingScore));
        read();
    }

    public String showRequest(String request) {
        String answer = "";

        while(answer.isBlank()) {
            print(request);
            answer = read();
        }

        return answer;
    }

    private void print(String dataForPrint) {
        System.out.println(dataForPrint);
    }

    private String read() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        return scanner.nextLine();
    }

}
