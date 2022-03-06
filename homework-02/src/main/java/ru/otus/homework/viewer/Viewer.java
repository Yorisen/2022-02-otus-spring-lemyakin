package ru.otus.homework.viewer;

import ru.otus.homework.domain.Student;
import ru.otus.homework.domain.TestQuestion;

import java.util.List;
import java.util.Scanner;

public class Viewer {
    Scanner scanner = null;

    public Student showIntroduceYourself() {
        print("Introduce yourself:");
        String name = showRequest("What is your name?");
        String surname = showRequest("What is your surname?");

        return new Student(name, surname);
    }

    public void showTheTestHasBegun() {
        print("\nThe test has begun...");
    }

    public void showTheTestIsOver() {
        print("\nThe test is over.\n");
    }

    public String showRequest(String request) {
        String answer = "";

        while(answer.isBlank()) {
            print(request);
            answer = read();
        }

        return answer;
    }

    public String showTestQuestion(TestQuestion testQuestion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nQuestion: ").append(testQuestion.getQuestion()).append("\n")
                .append("Answer options:\n");

        List<String> answerOptions = testQuestion.getAnswerOptions();
        int numberOfAnswerOptions = answerOptions.size();

        for (int answerNumber = 0; numberOfAnswerOptions > answerNumber; answerNumber++) {
            stringBuilder.append(answerNumber).append(" - ").append(answerOptions.get(answerNumber)).append("\n");
        }
        print(stringBuilder.toString());

        boolean responseIsNumber = false;
        int responseNumber = -1;

        while (!responseIsNumber || (responseNumber < 0) || (responseNumber > numberOfAnswerOptions)) {
            String response = showRequest("Enter value from 0 to " + (numberOfAnswerOptions - 1));
            responseIsNumber = response.matches("\\d+");
            if (responseIsNumber) {
                responseNumber = Integer.parseInt(response);
            }
        }

        return answerOptions.get(responseNumber);
    }

    public void showResults(Student student, int numberOfQuestions, int numberOfCorrectAnswers, boolean isTestPassed) {
        print(student.getName() + " " + student.getSurname() +
                ", you correctly answered " + numberOfCorrectAnswers +
                " questions out of " + numberOfQuestions +
                ". You " + ((isTestPassed) ? "passed" : "did not pass") + " the test" );
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
