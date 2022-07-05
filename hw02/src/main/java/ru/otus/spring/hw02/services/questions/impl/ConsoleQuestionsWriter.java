package ru.otus.spring.hw02.services.questions.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.services.questions.QuestionsWriter;

import java.io.PrintStream;
import java.util.List;

@Service
public class ConsoleQuestionsWriter implements QuestionsWriter {

    private final PrintStream out;

    public ConsoleQuestionsWriter(@Value("#{T(System).out}") PrintStream out) {
        this.out = out;
    }

    @Override
    public void write(Question question) {
        writeQuestionValue(question.getValue(), question.getOrder());
        writeAnswers(question.getAnswers());
    }

    private void writeQuestionValue(String value, int order) {
        out.printf("%d. %s%n", order, value);
    }

    private void writeAnswers(List<Answer> answers) {
        for (int index = 0; index < answers.size(); index++) {
            Answer answer = answers.get(index);
            out.printf("\t%d. %s%n", index + 1, answer.getValue());
        }
    }
}
