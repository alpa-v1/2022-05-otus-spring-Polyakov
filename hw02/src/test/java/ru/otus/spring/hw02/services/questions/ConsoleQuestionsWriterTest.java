package ru.otus.spring.hw02.services.questions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.impl.ConsoleQuestionsWriter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static ru.otus.spring.hw02.asserts.ConsoleOutputAssert.assertThat;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestion;

@DisplayName("Console questions writer")
class ConsoleQuestionsWriterTest {

    @Test
    @DisplayName("should correctly write question and answer to the output stream")
    void shouldWriteQuestionWithAnswerInCorrectFormat() {
        // given
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ConsoleQuestionsWriter consoleQuestionsWriter = new ConsoleQuestionsWriter(new PrintStream(out));

        // when
        Question question = getQuestion();
        consoleQuestionsWriter.write(question);

        // then
        String expectedOutput = """
                  1. Is it possible to have multiple inheritance in Java?
                  	1. no
                  	2. yes
                """;
        assertThat(out).isOutputWithoutSeparatorsEqualTo(expectedOutput);
    }
}
