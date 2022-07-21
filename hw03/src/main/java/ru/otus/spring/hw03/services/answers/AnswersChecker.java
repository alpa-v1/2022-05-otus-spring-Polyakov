package ru.otus.spring.hw03.services.answers;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.config.StudentsSurveyApplicationProperties;
import ru.otus.spring.hw03.config.properties.AnswerProperties;
import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.domain.SurveyResult;

import java.util.List;

@Service
public class AnswersChecker {

    private final AnswerProperties answerProperties;

    public AnswersChecker(StudentsSurveyApplicationProperties properties) {
        this.answerProperties = properties.getAnswers();
    }

    public SurveyResult check(Student student, List<Answer> answers) {
        long correctAnswersCount = answers.stream().filter(Answer::isCorrect).count();
        return new SurveyResult(student, correctAnswersCount >= answerProperties.getCountToPass(), correctAnswersCount);
    }
}
