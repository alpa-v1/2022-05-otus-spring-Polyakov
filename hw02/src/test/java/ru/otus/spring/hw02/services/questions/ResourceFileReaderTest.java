package ru.otus.spring.hw02.services.questions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.services.questions.impl.ResourceFileReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestionLinesWithHeader;

@DisplayName("Resource file reader")
public class ResourceFileReaderTest {

    @Test
    @DisplayName("should correctly read questions from csv file")
    void shouldCorrectlyReadQuestionsFromCsvFile() {
        // given
        ResourceFileReader resourceFileReader = new ResourceFileReader();

        // when
        List<String> lines = resourceFileReader.readAllLines("/questions.csv");

        // then
        List<String> expectedLines = getQuestionLinesWithHeader();
        assertThat(lines).isEqualTo(expectedLines);
    }
}
