package ru.otus.spring.hw01.services.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.hw01.domain.Question;
import ru.otus.spring.hw01.services.QuestionsReader;

import java.util.List;

@RequiredArgsConstructor
public class CsvFileReader implements QuestionsReader {

    private final String filepath;
    private final CsvQuestionsParser questionsParser;
    private final ResourceFileReader resourceFileReader;

    @Override
    public List<Question> read() {
        List<String> lines = resourceFileReader.readAllLines(filepath);
        return questionsParser.parse(lines);
    }
}
