package ru.otus.spring.hw02.services.questions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.impl.CsvQuestionsParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestionLinesWithHeader;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestions;

@DisplayName("Csv questions parser")
class CsvQuestionsParserTest {

    @Test
    @DisplayName("should correctly parse lines to questions")
    void shouldCorrectlyParseLinesToQuestions() {
        // given
        CsvQuestionsParser questionsParser = new CsvQuestionsParser("-", ",", ";");

        // when
        List<Question> questions = questionsParser.parse(getQuestionLinesWithHeader());

        // then
        List<Question> expectedQuestions = getQuestions();
        assertThat(questions).isEqualTo(expectedQuestions);
    }
}
