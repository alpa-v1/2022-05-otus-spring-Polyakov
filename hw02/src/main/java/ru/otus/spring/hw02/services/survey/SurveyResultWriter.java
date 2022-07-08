package ru.otus.spring.hw02.services.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.io.InputOutputService;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SurveyResultWriter {

    private final InputOutputService io;

    public void write(SurveyResult surveyResult) {
        io.write("\nSurvey finished!");
        Student student = surveyResult.getStudent();
        if (surveyResult.isPassed()) {
            io.write(format("Congratulations %s! You passed the survey!\n", student.getFullName()));
        } else {
            io.write(format("Nice try %s! But unfortunately, you didn't pass the survey.\n", student.getFullName()));
        }
        io.write(format("Number of correct questions - %d\n", surveyResult.getCorrectAnswers()));
    }
}
