package ru.otus.spring.hw02.services.students;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.services.questions.QuestionsWriter;
import ru.otus.spring.hw02.services.students.impl.ConsoleStudentInputService;
import ru.otus.spring.hw02.services.students.impl.StudentInputValidationService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Console student input service")
public class ConsoleStudentInputServiceTest {

    @Test
    @DisplayName("should correctly return user input name")
    void shouldCorrectlyReturnUserInputName() {
        // given
        InputStream in = new ByteArrayInputStream("Test".getBytes());
        PrintStream out = mock(PrintStream.class);
        QuestionsWriter questionsWriter = mock(QuestionsWriter.class);
        StudentInputValidationService studentInputValidationService = mock(StudentInputValidationService.class);
        when(studentInputValidationService.validateStudentName(anyString())).thenReturn(true);
        ConsoleStudentInputService consoleStudentInputService = new ConsoleStudentInputService(in, out, questionsWriter, studentInputValidationService);

        // when
        String name = consoleStudentInputService.askName("First Name");

        // then
        String expectedName = "Test";
        assertThat(name).isEqualTo(expectedName);
    }
}
