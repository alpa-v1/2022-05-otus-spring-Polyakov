package ru.otus.spring.hw06.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw06.core.transactionmanager.TransactionManager;
import ru.otus.spring.hw06.domain.Comment;
import ru.otus.spring.hw06.repository.CommentRepository;
import ru.otus.spring.hw06.services.CommentsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentRepository commentRepository;
    private final TransactionManager transactionManager;

    @Override
    public List<Comment> findAll() {
        return transactionManager.doInReadOnlyTransaction(commentRepository::findAll);
    }

    @Override
    public Comment findById(long id) {
        return transactionManager.doInReadOnlyTransaction(() -> commentRepository.findById(id));
    }

    @Override
    public Comment create(Comment comment) {
        return transactionManager.doInTransaction(() -> commentRepository.create(comment));
    }

    @Override
    public Comment update(Comment comment) {
        return transactionManager.doInTransaction(() -> commentRepository.update(comment));
    }

    @Override
    public void deleteById(long id) {
        transactionManager.doInTransaction(() -> commentRepository.deleteById(id));
    }
}
