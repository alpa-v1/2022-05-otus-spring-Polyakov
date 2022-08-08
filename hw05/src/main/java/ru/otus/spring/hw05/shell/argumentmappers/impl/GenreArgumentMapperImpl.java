package ru.otus.spring.hw05.shell.argumentmappers.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.hw05.domain.Genre;
import ru.otus.spring.hw05.shell.argumentmappers.GenreArgumentMapper;

@Service
public class GenreArgumentMapperImpl implements GenreArgumentMapper {

    @Override
    public Genre map(long id, String name) {
        return new Genre(id, name);
    }

    @Override
    public Genre map(String name) {
        return new Genre(name);
    }
}
