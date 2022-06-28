package ru.otus.spring.hw01.exception;

public class IncorrectQuestionFormatException extends RuntimeException {

    public IncorrectQuestionFormatException(String message) {
        super(message);
    }
}
