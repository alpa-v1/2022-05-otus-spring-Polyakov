package ru.otus.spring.hw06.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.core.transactionmanager.TransactionManager;
import ru.otus.spring.hw06.repository.GenreRepository;
import ru.otus.spring.hw06.domain.Genre;
import ru.otus.spring.hw06.services.GenresService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenresServiceImpl implements GenresService {

    private final GenreRepository genreRepository;
    private final TransactionManager transactionManager;

    @Override
    public List<Genre> findAll() {
        return transactionManager.doInReadOnlyTransaction(genreRepository::findAll);
    }

    @Override
    public Genre findById(long id) {
        return transactionManager.doInReadOnlyTransaction(() -> genreRepository.findById(id));
    }

    @Override
    public Genre create(Genre genre) {
        return transactionManager.doInTransaction(() -> genreRepository.create(genre));
    }

    @Override
    public Genre update(Genre genre) {
        return transactionManager.doInTransaction(() -> genreRepository.update(genre));
    }

    @Override
    public void deleteById(long id) {
        transactionManager.doInTransaction(() -> genreRepository.deleteById(id));
    }
}
