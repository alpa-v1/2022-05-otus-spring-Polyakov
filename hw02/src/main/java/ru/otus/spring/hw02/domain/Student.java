package ru.otus.spring.hw02.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Data
@RequiredArgsConstructor
public class Student {

    private final String firstname;
    private final String lastname;

    public String getFullName() {
        return format("%s %s",
                ofNullable(firstname).orElse(StringUtils.EMPTY),
                ofNullable(lastname).orElse(StringUtils.EMPTY)
        );
    }
}
