package ru.otus.spring.hw03.services.questions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.hw03.domain.Question;
import ru.otus.spring.hw03.services.questions.impl.CsvFileQuestionsReader;
import ru.otus.spring.hw03.services.questions.impl.CsvQuestionsParser;
import ru.otus.spring.hw03.services.questions.impl.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static ru.otus.spring.hw03.util.QuestionsDataGenerator.getQuestions;

@SpringBootTest
@DisplayName("Given csv file questions reader")
class CsvFileQuestionsReaderTest {

    @MockBean
    ResourceFileReader resourceFileReader;

    @MockBean
    CsvQuestionsParser questionsParser;

    @Autowired
    CsvFileQuestionsReader csvFileQuestionsReader;

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
