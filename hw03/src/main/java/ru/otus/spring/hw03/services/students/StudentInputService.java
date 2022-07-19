package ru.otus.spring.hw03.services.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.domain.Answer;
import ru.otus.spring.hw03.domain.Question;
import ru.otus.spring.hw03.services.i18n.I18nService;
import ru.otus.spring.hw03.services.io.InputOutputService;
import ru.otus.spring.hw03.services.questions.QuestionsWriter;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class StudentInputService {

    private final I18nService i18n;
    private final InputOutputService io;
    private final QuestionsWriter questionsWriter;
    private final StudentInputValidationService studentInputValidationService;

    public String askName(String type) {
        io.write(format("%s - ", type));
        String name = io.readLine();
        while (!studentInputValidationService.validateStudentName(name)) {
            io.write(format("%s\n", i18n.getMessage("output.student.input.incorrect-format", name)));
            io.write(format("%s - ", type));
            name = io.readLine();
        }
        return name;
    }

    public Answer askQuestion(Question question) {
        questionsWriter.write(question);
        io.write(format("%s - ", i18n.getMessage("output.student.input.answer")));
        String indexOfAnswer = io.readLine();
        while (!studentInputValidationService.validateSelectedAnswer(indexOfAnswer, question.getAnswersSize())) {
            io.write(format("%s\n", i18n.getMessage("output.student.input.incorrect-format", indexOfAnswer)));
            io.write(format("%s - ", i18n.getMessage("output.student.input.answer")));
            indexOfAnswer = io.readLine();
        }
        return question.getAnswer(Integer.parseInt(indexOfAnswer) - 1);
    }

    public boolean askContinue() {
        io.write(format("%s\n", i18n.getMessage("output.student.input.ask.continue")));
        io.write(format("%s - ", i18n.getMessage("output.student.input.enter.continue")));
        String continueCommand = io.readLine();
        while (!studentInputValidationService.validateContinueCommand(continueCommand)) {
            io.write(format("%s\n", i18n.getMessage("output.student.input.incorrect-format", continueCommand)));
            io.write(format("%s - ", i18n.getMessage("output.student.input.enter.continue")));
            continueCommand = io.readLine();
        }
        return continueCommand.equalsIgnoreCase(i18n.getMessage("output.student.input.enter.yes"));
    }
}
