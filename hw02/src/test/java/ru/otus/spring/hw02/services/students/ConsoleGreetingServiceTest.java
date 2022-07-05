package ru.otus.spring.hw02.services.students;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.services.students.impl.ConsoleGreetingService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static ru.otus.spring.hw02.asserts.ConsoleOutputAssert.assertThat;

@DisplayName("Console greeting service")
public class ConsoleGreetingServiceTest {

    @Test
    @DisplayName("should write correct content for anonymous user to the output stream")
    void shouldWriteCorrectContentForAnonymousUserStudent() {
        // given
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsoleGreetingService consoleGreetingService = new ConsoleGreetingService(new PrintStream(out));

        // when
        consoleGreetingService.greet();

        // then
        String expectedOutput = "Welcome to the Java Survey! Please provide the following details to start:";
        assertThat(out).isOutputWithoutSeparatorsEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("should write correct content for student to the output stream")
    void shouldWriteCorrectContentForStudent() {
        // given
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsoleGreetingService consoleGreetingService = new ConsoleGreetingService(new PrintStream(out));

        // when
        Student student = getStudent();
        consoleGreetingService.greet(student, 5);

        // then
        String expectedOutput = "Hello Test Student! We prepared 5 questions for you. Please choose one possible answer from the existing ones. Let's start!";
        assertThat(out).isOutputWithoutSeparatorsEqualTo(expectedOutput);
    }

    private Student getStudent() {
        return new Student("Test", "Student");
    }
}
