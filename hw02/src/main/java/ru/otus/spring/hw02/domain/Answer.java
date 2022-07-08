package ru.otus.spring.hw02.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Answer {

    private final String value;
    private final boolean correct;
}
