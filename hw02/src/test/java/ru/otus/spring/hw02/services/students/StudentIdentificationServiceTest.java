package ru.otus.spring.hw02.services.students;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import ru.otus.spring.hw02.domain.Student;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@MockitoSettings
@DisplayName("Given student identification service")
class StudentIdentificationServiceTest {

    @Mock
    StudentInputService studentInputService;

    @InjectMocks
    StudentIdentificationService studentIdentificationService;

    @Test
    @DisplayName("when student should be identified, then return student object with specified first and last name")
    void when_StudentShouldBeIdentified_then_ReturnStudentObjectWithSpecifiedFirstAndLastName() {
        // given
        given(studentInputService.askName(anyString())).willReturn("First").willReturn("Student");

        // when
        Student actualStudent = studentIdentificationService.identify();

        // then
        Student expectedStudent = getStudent();
        assertThat(actualStudent).isEqualTo(expectedStudent);
    }

    Student getStudent() {
        return new Student("First", "Student");
    }
}
