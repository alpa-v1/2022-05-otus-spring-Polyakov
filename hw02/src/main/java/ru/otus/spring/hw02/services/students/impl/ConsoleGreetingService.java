package ru.otus.spring.hw02.services.students.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.services.students.GreetingService;

import java.io.PrintStream;

@Service
public class ConsoleGreetingService implements GreetingService {

    private final PrintStream out;

    public ConsoleGreetingService(@Value("#{T(System).out}") PrintStream out) {
        this.out = out;
    }

    @Override
    public void greet() {
        out.println("Welcome to the Java Survey! Please provide the following details to start:");
    }

    @Override
    public void greet(Student student, int numberOfQuestions) {
        out.printf("\nHello %s! We prepared %d questions for you. Please choose one possible answer from the existing ones. Let's start!\n",
                student.getFullName(), numberOfQuestions);
    }
}
