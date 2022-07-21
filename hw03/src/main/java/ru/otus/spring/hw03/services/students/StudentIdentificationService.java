package ru.otus.spring.hw03.services.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Student;
import ru.otus.spring.hw03.services.i18n.I18nService;

@Service
@RequiredArgsConstructor
public class StudentIdentificationService {

    private final I18nService i18n;
    private final StudentInputService studentInputService;

    public Student identify() {
        String firstname = studentInputService.askName(i18n.getMessage("output.student.identification.firstname"));
        String lastname = studentInputService.askName(i18n.getMessage("output.student.identification.lastname"));
        return new Student(firstname, lastname);
    }
}
