package ru.otus.spring.hw02.services.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Student;

@Service
@RequiredArgsConstructor
public class StudentIdentificationService {

    private final StudentInputService studentInputService;

    public Student identify() {
        String firstname = studentInputService.askName("First name");
        String lastname = studentInputService.askName("Last name");
        return new Student(firstname, lastname);
    }
}
