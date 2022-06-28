package ru.otus.spring.hw01.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.otus.spring.hw01.domain.Question;
import ru.otus.spring.hw01.exception.CsvFileNotFoundException;
import ru.otus.spring.hw01.exception.CsvFileReadException;
import ru.otus.spring.hw01.services.QuestionsReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static ru.otus.spring.hw01.util.Validations.requireNonNull;

@RequiredArgsConstructor
public class CsvFileReader implements QuestionsReader {

    private final String filepath;
    private final QuestionsParser questionsParser;

    @Override
    public List<Question> read() {
        try (InputStream inputStream = getResourceInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            List<String> lines = readAllLines(bufferedReader);
            return questionsParser.parse(lines);

        } catch (IOException e) {
            throw new CsvFileReadException(String.format("Unable to read csv file by path [%s]", filepath));
        }
    }

    private List<String> readAllLines(BufferedReader bufferedReader) {
        return bufferedReader
                .lines()
                .skip(1)
                .filter(StringUtils::isNotEmpty)
                .toList();
    }

    private InputStream getResourceInputStream() {
        InputStream inputStream = getClass().getResourceAsStream(filepath);
        requireNonNull(inputStream, CsvFileNotFoundException::new);
        return inputStream;
    }
}
