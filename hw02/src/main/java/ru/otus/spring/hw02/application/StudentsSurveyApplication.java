package ru.otus.spring.hw02.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.students.GreetingService;
import ru.otus.spring.hw02.services.students.StudentInputService;
import ru.otus.spring.hw02.services.students.impl.StudentIdentificationService;
import ru.otus.spring.hw02.services.survey.SurveyResultWriter;
import ru.otus.spring.hw02.services.survey.impl.StudentSurveyService;

@Service
@RequiredArgsConstructor
public class StudentsSurveyApplication {

    private final GreetingService greetingService;
    private final SurveyResultWriter surveyResultWriter;
    private final StudentInputService studentInputService;
    private final StudentSurveyService studentSurveyService;
    private final StudentIdentificationService studentIdentificationService;

    public void run() {
        do {
            greetingService.greet();
            Student student = studentIdentificationService.identify();
            SurveyResult surveyResult = studentSurveyService.start(student);
            surveyResultWriter.write(surveyResult);
        } while (studentInputService.askContinue());
    }
}
