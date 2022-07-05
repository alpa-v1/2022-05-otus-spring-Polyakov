package ru.otus.spring.hw02.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SurveyResult {

    private final Student student;
    private final boolean passed;
    private final long correctAnswers;
}
