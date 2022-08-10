package ru.otus.spring.hw06.core.transactionmanager.actions;

import java.util.function.Supplier;

public interface TransactionAction<T> extends Supplier<T> {
}
