package ru.otus.spring.hw01.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw01.domain.Question;
import ru.otus.spring.hw01.services.impl.CsvFileReader;
import ru.otus.spring.hw01.services.impl.QuestionsParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("CSV file reader")
class CsvFileReaderTest {

    @Test
    @DisplayName("should correctly read questions from csv file")
    void shouldCorrectlyReadQuestionsFromCsvFile() {
        QuestionsParser questionsParser = mock(QuestionsParser.class);
        when(questionsParser.parse(anyList())).thenReturn(getQuestions());
        CsvFileReader csvFileReader = new CsvFileReader("/questions.csv", questionsParser);

        List<Question> questions = csvFileReader.read();

        List<Question> expectedQuestions = getQuestions();
        assertThat(questions).isEqualTo(expectedQuestions);
    }

    private List<Question> getQuestions() {
        return List.of(
                new Question("How many OOP principles exist?", List.of("one", "three", "five", "seven", "nine")),
                new Question("Is it possible to have multiple inheritance in Java?", List.of("no", "yes")),
                new Question("What design pattern name is correct?", List.of("Designer", "Element", "Adapter")),
                new Question("How many primitive types java has?", List.of("five", "four", "eight", "seven")),
                new Question("Which class do all classes in Java extend?", List.of("Proxy", "Object", "Class", "Entity"))
        );
    }
}
