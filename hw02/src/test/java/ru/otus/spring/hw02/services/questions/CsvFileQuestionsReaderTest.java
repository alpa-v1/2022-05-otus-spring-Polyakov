package ru.otus.spring.hw02.services.questions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.impl.CsvFileQuestionsReader;
import ru.otus.spring.hw02.services.questions.impl.CsvQuestionsParser;
import ru.otus.spring.hw02.services.questions.impl.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestionLines;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestions;

@DisplayName("Csv file questions reader")
class CsvFileQuestionsReaderTest {

    @Test
    @DisplayName("should correctly read questions from csv file")
    void shouldCorrectlyReadQuestionsFromCsvFile() {
        // given
        ResourceFileReader resourceFileReader = mock(ResourceFileReader.class);
        when(resourceFileReader.readAllLines(anyString())).thenReturn(getQuestionLines());
        CsvQuestionsParser questionsParser = mock(CsvQuestionsParser.class);
        when(questionsParser.parse(anyList())).thenReturn(getQuestions());
        CsvFileQuestionsReader csvFileQuestionsReader = new CsvFileQuestionsReader("/questions.csv", questionsParser, resourceFileReader);

        // when
        List<Question> questions = csvFileQuestionsReader.read();

        // then
        List<Question> expectedQuestions = getQuestions();
        assertThat(questions).isEqualTo(expectedQuestions);
    }
}
