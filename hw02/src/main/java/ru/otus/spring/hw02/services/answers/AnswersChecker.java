package ru.otus.spring.hw02.services.answers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;

import java.util.List;

@Service
public class AnswersChecker {

    private final int countOfCorrectAnswersToPass;

    public AnswersChecker(@Value("${answers.count-to-pass}") int countOfCorrectAnswersToPass) {
        this.countOfCorrectAnswersToPass = countOfCorrectAnswersToPass;
    }

    public SurveyResult check(Student student, List<Answer> answers) {
        long correctAnswersCount = answers.stream().filter(Answer::isCorrect).count();
        return new SurveyResult(student, correctAnswersCount >= countOfCorrectAnswersToPass, correctAnswersCount);
    }
}
