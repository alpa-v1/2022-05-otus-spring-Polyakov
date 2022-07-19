package ru.otus.spring.hw03.services.questions.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.config.StudentsSurveyApplicationProperties;
import ru.otus.spring.hw03.config.properties.QuestionProperties;
import ru.otus.spring.hw03.domain.Question;
import ru.otus.spring.hw03.services.questions.QuestionsReader;

import java.util.List;

@Service
public class CsvFileQuestionsReader implements QuestionsReader {

    private final CsvQuestionsParser questionsParser;
    private final ResourceFileReader resourceFileReader;
    private final QuestionProperties questionProperties;

    public CsvFileQuestionsReader(CsvQuestionsParser questionsParser,
                                  ResourceFileReader resourceFileReader,
                                  StudentsSurveyApplicationProperties properties) {
        this.questionProperties = properties.getQuestions();
        this.questionsParser = questionsParser;
        this.resourceFileReader = resourceFileReader;
    }

    @Override
    public List<Question> read() {
        List<String> lines = resourceFileReader.readAllLines(questionProperties.getFilepath());
        return questionsParser.parse(lines);
    }
}
