package ru.otus.spring.hw02.services.survey.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.survey.SurveyResultWriter;

import java.io.PrintStream;

@Service
public class ConsoleSurveyResultWriter implements SurveyResultWriter {

    private final PrintStream out;

    public ConsoleSurveyResultWriter(@Value("#{T(System).out}") PrintStream out) {
        this.out = out;
    }

    @Override
    public void write(SurveyResult surveyResult) {
        out.println("\nSurvey finished!");
        Student student = surveyResult.getStudent();
        if (surveyResult.isPassed()) {
            out.printf("Congratulations %s! You passed the survey!\n", student.getFullName());
        } else {
            out.printf("Nice try %s! But unfortunately, you didn't pass the survey.\n", student.getFullName());
        }
        out.printf("Number of correct questions - %d\n", surveyResult.getCorrectAnswers());
    }
}
