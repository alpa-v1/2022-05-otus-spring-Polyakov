package ru.otus.spring.hw02.services.answers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Answers checker")
public class AnswersCheckerTest {

    @Test
    @DisplayName("should check that student passed the survey if he has specified amount of correct answers")
    void shouldCheckThatStudentCanPassTheSurvey() {
        // given
        AnswersChecker answersChecker = new AnswersChecker(4);

        // when
        Student student = getStudent();
        List<Answer> answers = getAnswers();
        SurveyResult surveyResult = answersChecker.check(student, answers);

        // then
        SurveyResult passedSurveyResult = getPassedSurveyResult(student);
        assertThat(surveyResult).isEqualTo(passedSurveyResult);
    }

    private Student getStudent() {
        return new Student("Test", "Student");
    }

    private List<Answer> getAnswers() {
        return List.of(
                new Answer("one", true),
                new Answer("three", true),
                new Answer("five", true),
                new Answer("seven", true),
                new Answer("nine", false)
        );
    }

    private SurveyResult getPassedSurveyResult(Student student) {
        return new SurveyResult(student, true, 4);
    }
}
