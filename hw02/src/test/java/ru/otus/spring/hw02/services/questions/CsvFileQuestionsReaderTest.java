package ru.otus.spring.hw02.services.questions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.impl.CsvFileQuestionsReader;
import ru.otus.spring.hw02.services.questions.impl.CsvQuestionsParser;
import ru.otus.spring.hw02.services.questions.impl.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestions;

@MockitoSettings
@DisplayName("Given csv file questions reader")
class CsvFileQuestionsReaderTest {

    @Mock
    ResourceFileReader resourceFileReader;

    @Mock
    CsvQuestionsParser questionsParser;

    CsvFileQuestionsReader csvFileQuestionsReader;

    @BeforeEach
    void setup() {
        csvFileQuestionsReader = new CsvFileQuestionsReader("/questions.csv", questionsParser, resourceFileReader);
    }

    @Test
    @DisplayName("when csv file is present, then read and return all questions as objects")
    void when_CsvFileIsPresent_then_ReadAndReturnAllQuestionsAsObjects() {
        // given
        given(questionsParser.parse(anyList())).willReturn(getQuestions());

        // when
        List<Question> questions = csvFileQuestionsReader.read();

        // then
        List<Question> expectedQuestions = getQuestions();
        assertThat(questions).isEqualTo(expectedQuestions);
    }
}
