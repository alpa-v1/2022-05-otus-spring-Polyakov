package ru.otus.spring.hw02.services.questions.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw02.domain.Answer;
import ru.otus.spring.hw02.domain.Question;
import ru.otus.spring.hw02.exception.IncorrectQuestionFormatException;
import ru.otus.spring.hw02.services.questions.QuestionsParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;
import static ru.otus.spring.hw02.util.Validations.*;

@Service
public class CsvQuestionsParser implements QuestionsParser {

    private static final int VALID_SPLIT_LINE_LENGTH = 3;

    private final String correctAnswerSeparator;
    private final String questionsSeparator;
    private final String answersSeparator;

    public CsvQuestionsParser(@Value("${answers.correct-marker.separator}") String correctAnswerSeparator,
                              @Value("${questions.separator}") String questionsSeparator,
                              @Value("${answers.separator}") String answersSeparator) {
        this.correctAnswerSeparator = correctAnswerSeparator;
        this.questionsSeparator = questionsSeparator;
        this.answersSeparator = answersSeparator;
    }

    @Override
    public List<Question> parse(List<String> lines) {
        requireNonEmpty(lines, () -> new IncorrectQuestionFormatException("Questions should not be empty"));
        return lines.stream()
                .filter(StringUtils::isNotEmpty)
                .skip(1)
                .map(this::parse)
                .sorted(Comparator.comparingInt(Question::getOrder))
                .toList();
    }

    @Override
    public Question parse(String line) {
        String[] questionElements = line.split(questionsSeparator);
        requireSize(questionElements, VALID_SPLIT_LINE_LENGTH,
                () -> new IncorrectQuestionFormatException(format("Some mandatory field is missing, please check format for - [%s]", line)));
        String order = questionElements[0];
        String questionValue = questionElements[1];
        String answersLine = questionElements[2];
        return new Question(parseOrder(order, line), questionValue, parseAnswers(answersLine, line));
    }

    private int parseOrder(String order, String line) {
        try {
            return Integer.parseInt(order);
        } catch (NumberFormatException e) {
            throw new IncorrectQuestionFormatException(format("incorrect format for order [%s] in line - [%s]", order, line));
        }
    }

    private List<Answer> parseAnswers(String answersLine, String line) {
        List<Answer> answers = new ArrayList<>();
        String[] answerLines = answersLine.split(answersSeparator);
        for (String answerLine : answerLines) {
            String[] markedAnswer = answerLine.split(correctAnswerSeparator);
            String answerValue = markedAnswer[0];
            boolean correct = false;
            if (markedAnswer.length == 2) {
                String state = markedAnswer[1];
                requireBoolean(state,
                        () -> new IncorrectQuestionFormatException(format("Incorrect value for answer mark [%s], in line - [%s]", state, line)));
                correct = Boolean.parseBoolean(state);
            }
            Answer answer = new Answer(answerValue, correct);
            answers.add(answer);
        }
        return answers;
    }
}
