package ru.otus.spring.hw02.services.questions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.io.InputOutputService;
import ru.otus.spring.hw02.services.questions.impl.ConsoleQuestionsWriter;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static ru.otus.spring.hw02.util.QuestionsDataGenerator.getQuestion;

@MockitoSettings
@DisplayName("Given console questions writer")
class ConsoleQuestionsWriterTest {

    @Mock
    InputOutputService io;

    @InjectMocks
    ConsoleQuestionsWriter consoleQuestionsWriter;

    @Test
    @DisplayName("when question object passed to write, then write question value")
    void when_QuestionObjectPassedToWrite_then_WriteQuestionValue() {
        // when
        Question question = getQuestion();
        consoleQuestionsWriter.write(question);

        // then
        verify(io).write(contains(question.getValue()));
    }
}
