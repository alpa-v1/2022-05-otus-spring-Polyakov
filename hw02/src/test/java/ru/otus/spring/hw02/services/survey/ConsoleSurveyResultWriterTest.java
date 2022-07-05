package ru.otus.spring.hw02.services.survey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.domain.SurveyResult;
import ru.otus.spring.hw02.services.survey.impl.ConsoleSurveyResultWriter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static ru.otus.spring.hw02.asserts.ConsoleOutputAssert.assertThat;

@DisplayName("Console survey result writer")
public class ConsoleSurveyResultWriterTest {

    @Test
    @DisplayName("should correctly write passed survey result to the output stream")
    void shouldCorrectlyWriteSurveyPassedResultToTheOutputStream() {
        // given
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsoleSurveyResultWriter consoleSurveyResultWriter = new ConsoleSurveyResultWriter(new PrintStream(out));

        // when
        SurveyResult surveyResult = getSurveyResult();
        consoleSurveyResultWriter.write(surveyResult);

        // then
        String expectedOutput = """
                Survey finished!
                Congratulations Test Student! You passed the survey!
                Number of correct questions - 4
                """;
        assertThat(out).isOutputWithoutSeparatorsEqualTo(expectedOutput);
    }

    private SurveyResult getSurveyResult() {
        return new SurveyResult(new Student("Test", "Student"), true, 4);
    }
}
