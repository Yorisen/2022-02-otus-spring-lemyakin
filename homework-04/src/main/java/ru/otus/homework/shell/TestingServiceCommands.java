package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.domain.Student;
import ru.otus.homework.service.TestingService;
import ru.otus.homework.service.TestingServiceImpl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ShellComponent
@RequiredArgsConstructor
public class TestingServiceCommands {
    private final TestingService testingService;
    private Student student = null;

    @ShellMethod(value = "Login command, name and surname required", key = {"login", "l"})
    public String login(@ShellOption({"-n", "--name"}) String name,
                      @ShellOption({"-s", "--surname"}) String surname) {
            student = new Student(name, surname);
            return String.format("%s, you have successfully logged in.\n" +
                "To start testing, enter the command t or testing.", name);
    }

    @ShellMethod(value = "Start testing", key = {"testing", "t"})
    @ShellMethodAvailability(value = "isStartTestingAvailable")
    public void testing() {
        testingService.run(student);
    }

    private Availability isStartTestingAvailable() {
        return (student == null) ?
                Availability.unavailable("you need to login before testing. Use l or login command.") :
                Availability.available();
    }
}
