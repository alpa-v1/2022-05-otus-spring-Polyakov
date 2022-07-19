package ru.otus.spring.hw03.services.students;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.services.i18n.I18nService;

@Service
@RequiredArgsConstructor
public class StudentInputValidationService {

    private final I18nService i18n;

    public boolean validateStudentName(String name) {
        return StringUtils.isNotEmpty(name) && name.matches(i18n.getMessage("validation.regex.student.input.name"));
    }

    public boolean validateSelectedAnswer(String selectedAnswer, int answersSize) {
        if (StringUtils.isNumeric(selectedAnswer)) {
            int selectedAnswerIndex = Integer.parseInt(selectedAnswer);
            return selectedAnswerIndex > 0 && selectedAnswerIndex <= answersSize;
        }
        return false;
    }

    public boolean validateContinueCommand(String continueCommand) {
        return StringUtils.isNotEmpty(continueCommand)
               && (continueCommand.equalsIgnoreCase(i18n.getMessage("output.student.input.enter.yes"))
                   || continueCommand.equalsIgnoreCase(i18n.getMessage("output.student.input.enter.no")));
    }
}
