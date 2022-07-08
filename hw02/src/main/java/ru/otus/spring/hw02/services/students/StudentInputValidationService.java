package ru.otus.spring.hw02.services.students;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentInputValidationService {

    public boolean validateStudentName(String name) {
        return StringUtils.isNotEmpty(name) && name.matches("^[a-zA-Z]{2,20}$");
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
               && (continueCommand.equalsIgnoreCase("y") || continueCommand.equalsIgnoreCase("n"));
    }
}
