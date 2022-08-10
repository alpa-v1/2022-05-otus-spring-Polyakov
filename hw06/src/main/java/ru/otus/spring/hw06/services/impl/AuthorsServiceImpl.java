package ru.otus.spring.hw06.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.core.transactionmanager.TransactionManager;
import ru.otus.spring.hw06.domain.Author;
import ru.otus.spring.hw06.repository.AuthorRepository;
import ru.otus.spring.hw06.services.AuthorsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorRepository authorRepository;
    private final TransactionManager transactionManager;

    @Override
    public List<Author> findAll() {
        return transactionManager.doInReadOnlyTransaction(authorRepository::findAll);
    }

    @Override
    public Author findById(long id) {
        return transactionManager.doInReadOnlyTransaction(() -> authorRepository.findById(id));
    }

    @Override
    public Author create(Author author) {
        return transactionManager.doInTransaction(() -> authorRepository.create(author));
    }

    @Override
    public Author update(Author author) {
        return transactionManager.doInTransaction(() -> authorRepository.update(author));
    }

    @Override
    public void deleteById(long id) {
        transactionManager.doInTransaction(() -> authorRepository.deleteById(id));
    }
}
