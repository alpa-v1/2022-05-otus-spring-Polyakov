package ru.otus.spring.hw02.services.students;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.hw02.services.students.impl.StudentInputValidationService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Student input validation service")
public class StudentInputValidationServiceTest {

    @Test
    @DisplayName("should correctly validate student name")
    void shouldCorrectlyValidateStudentName() {
        // given
        StudentInputValidationService studentInputValidationService = new StudentInputValidationService();

        // when
        boolean valid = studentInputValidationService.validateStudentName("Test");

        // then
        assertThat(valid).isTrue();
    }

}
