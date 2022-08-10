package ru.otus.spring.hw06.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.core.transactionmanager.TransactionManager;
import ru.otus.spring.hw06.repository.BookRepository;
import ru.otus.spring.hw06.domain.Book;
import ru.otus.spring.hw06.services.BooksService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

    private final BookRepository bookRepository;
    private final TransactionManager transactionManager;

    @Override
    public List<Book> findAll() {
        return transactionManager.doInReadOnlyTransaction(bookRepository::findAll);
    }

    @Override
    public Book findById(long id) {
        return transactionManager.doInReadOnlyTransaction(() -> bookRepository.findById(id));
    }

    @Override
    public Book create(Book book) {
        return transactionManager.doInTransaction(() -> bookRepository.create(book));
    }

    @Override
    public Book update(Book book) {
        return transactionManager.doInTransaction(() -> bookRepository.update(book));
    }

    @Override
    public void deleteById(long id) {
        transactionManager.doInTransaction(() -> bookRepository.deleteById(id));
    }
}
