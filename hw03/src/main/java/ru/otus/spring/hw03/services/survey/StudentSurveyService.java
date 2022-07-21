package ru.otus.spring.hw03.services.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Question;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;
import ru.otus.spring.hw03.services.answers.AnswersChecker;
import ru.otus.spring.hw03.services.questions.QuestionsReader;
import ru.otus.spring.hw03.services.students.GreetingService;
import ru.otus.spring.hw03.services.students.StudentInputService;

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
