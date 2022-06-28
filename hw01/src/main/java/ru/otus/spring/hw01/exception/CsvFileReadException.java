package ru.otus.spring.hw01.exception;

public class CsvFileReadException extends RuntimeException {

    public CsvFileReadException(String message) {
        super(message);
    }
}
