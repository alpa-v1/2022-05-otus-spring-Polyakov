package ru.otus.spring.hw06.shell.argumentmappers;

import ru.otus.spring.hw06.domain.Genre;

public interface GenreArgumentMapper {

    Genre map(long id, String name);

    Genre map(String name);
}
