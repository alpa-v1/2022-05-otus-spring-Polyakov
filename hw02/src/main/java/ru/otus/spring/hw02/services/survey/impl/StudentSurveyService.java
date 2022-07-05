package ru.otus.spring.hw02.services.survey.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.answers.AnswersChecker;
import ru.otus.spring.hw02.services.questions.QuestionsReader;
import ru.otus.spring.hw02.services.students.GreetingService;
import ru.otus.spring.hw02.services.students.StudentInputService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentSurveyService {

    private final AnswersChecker answersChecker;
    private final GreetingService greetingService;
    private final QuestionsReader questionsReader;
    private final StudentInputService studentInputService;

    public SurveyResult start(Student student) {
        List<Question> questions = questionsReader.read();
        greetingService.greet(student, questions.size());
        List<Answer> answers = questions.stream().map(studentInputService::askQuestion).toList();
        return answersChecker.check(student, answers);
    }
}
