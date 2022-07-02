package ru.otus.spring.hw01.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Question {

    private final String value;
    private final List<String> answers;
}
