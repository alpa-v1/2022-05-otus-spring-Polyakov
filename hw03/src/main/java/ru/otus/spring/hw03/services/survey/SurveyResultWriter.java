package ru.otus.spring.hw03.services.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;
import ru.otus.spring.hw03.services.i18n.I18nService;
import ru.otus.spring.hw03.services.io.InputOutputService;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SurveyResultWriter {

    private final I18nService i18n;
    private final InputOutputService io;

    public void write(SurveyResult surveyResult) {
        io.write(format("%s\n", i18n.getMessage("output.survey.finished")));
        Student student = surveyResult.getStudent();
        if (surveyResult.isPassed()) {
            io.write(format("%s\n", i18n.getMessage("output.survey.passed", student.getFullName())));
        } else {
            io.write(format("%s\n", i18n.getMessage("output.survey.failed", student.getFullName())));
        }
        io.write(format("%s\n\n", i18n.getMessage("output.survey.number-of-correct-answers", surveyResult.getCorrectAnswers())));
    }
}
