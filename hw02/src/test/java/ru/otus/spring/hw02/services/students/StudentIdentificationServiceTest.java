package ru.otus.spring.hw02.services.students;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.domain.Student;
import ru.otus.spring.hw02.services.students.impl.StudentIdentificationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Student identification service")
public class StudentIdentificationServiceTest {

    @Test
    @DisplayName("should correctly identify student")
    void shouldCorrectlyIdentifyStudent() {
        // given
        StudentInputService studentInputService = mock(StudentInputService.class);
        when(studentInputService.askName(anyString())).thenReturn("Test");
        StudentIdentificationService studentIdentificationService = new StudentIdentificationService(studentInputService);

        // when
        Student identifiedStudent = studentIdentificationService.identify();

        // then
        Student expectedStudent = getStudent();
        assertThat(identifiedStudent).isEqualTo(expectedStudent);
    }

    private Student getStudent() {
        return new Student("Test", "Test");
    }
}
