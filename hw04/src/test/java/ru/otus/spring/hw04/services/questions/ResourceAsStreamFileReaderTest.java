package ru.otus.spring.hw04.services.questions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.hw04.exception.ResourceFileNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static ru.otus.spring.hw04.util.QuestionsDataGenerator.getCorrectlyFormattedQuestionLines;

@SpringBootTest
@DisplayName("Given resource file reader")
class ResourceAsStreamFileReaderTest {

    @Autowired
    private ResourceFileReader resourceFileReader;

    @Test
    @DisplayName("when resource file is present, then read content of file to list of strings")
    void when_ResourceFileIsPresent_then_ReadContentOfFileToListOfStrings() {
        // when
        List<String> lines = resourceFileReader.readAllLines("/questions.csv");

        // then
        List<String> expectedLines = getCorrectlyFormattedQuestionLines();
        assertThat(lines).isEqualTo(expectedLines);
    }

    @Test
    @DisplayName("when resource file is not present, then throw ResourceFileNotFoundException")
    void when_ResourceFileIsNotPresent_then_ThrowResourceFileNotFoundException() {
        // when
        Throwable throwable = catchThrowable(() -> resourceFileReader.readAllLines("not-exist-file"));

        // then
        assertThat(throwable).isInstanceOf(ResourceFileNotFoundException.class);
    }
}
