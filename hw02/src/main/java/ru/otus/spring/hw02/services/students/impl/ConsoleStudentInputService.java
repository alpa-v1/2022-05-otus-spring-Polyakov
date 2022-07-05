package ru.otus.spring.hw02.services.students.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.QuestionsWriter;
import ru.otus.spring.hw02.services.students.StudentInputService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleStudentInputService implements StudentInputService {

    private final Scanner in;
    private final PrintStream out;
    private final QuestionsWriter questionsWriter;
    private final StudentInputValidationService studentInputValidationService;

    public ConsoleStudentInputService(@Value("#{T(System).in}") InputStream in,
                                      @Value("#{T(System).out}") PrintStream out,
                                      QuestionsWriter questionsWriter,
                                      StudentInputValidationService studentInputValidationService) {
        this.in = new Scanner(in);
        this.out = out;
        this.questionsWriter = questionsWriter;
        this.studentInputValidationService = studentInputValidationService;
    }

    @Override
    public String askName(String type) {
        out.printf("%s - ", type);
        String name = in.nextLine();
        while (!studentInputValidationService.validateStudentName(name)) {
            out.printf("Incorrect format of input - [%s], please try again.\n", name);
            out.printf("%s - ", type);
            name = in.nextLine();
        }
        return name;
    }

    @Override
    public Answer askQuestion(Question question) {
        questionsWriter.write(question);
        out.print("Please choose the number of your answer - ");
        String indexOfAnswer = in.nextLine();
        while (!studentInputValidationService.validateSelectedAnswer(indexOfAnswer, question.getAnswersSize())) {
            out.printf("Incorrect format of input - [%s], please try again.\n", indexOfAnswer);
            out.print("Please choose the number of your answer - ");
            indexOfAnswer = in.nextLine();
        }
        return question.getAnswer(Integer.parseInt(indexOfAnswer) - 1);
    }

    @Override
    public boolean askContinue() {
        out.println("Do you want to start the survey again? please enter - y/n");
        String continueCommand = in.nextLine();
        while (!studentInputValidationService.validateContinueCommand(continueCommand)) {
            out.println("Incorrect format of input, please try again.");
            out.println("Please enter - y/n");
            continueCommand = in.nextLine();
        }
        return continueCommand.equalsIgnoreCase("y");
    }
}
