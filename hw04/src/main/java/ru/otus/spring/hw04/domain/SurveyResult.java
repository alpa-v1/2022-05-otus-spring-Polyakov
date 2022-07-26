package ru.otus.spring.hw04.domain;

import lombok.Data;

@Data
public class SurveyResult {

    private final Student student;
    private final boolean passed;
    private final long correctAnswers;
}
