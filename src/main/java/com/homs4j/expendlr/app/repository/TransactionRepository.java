package com.homs4j.expendlr.app.repository;

import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.model.Transaction;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    public Optional<Transaction> save(Transaction transaction);
    public Optional<Transaction> update(Transaction transaction);
    public List<Transaction> findAllByDate(Timestamp start, Timestamp end, String accountId);
    public Optional<Transaction> findById(String id);
    public int deleteById(String id);
    public int updateTransactionStatus(String id, TransactionStatus status);
}
