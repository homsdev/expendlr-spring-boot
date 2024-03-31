package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.enums.TransactionStatus;
import com.homs4j.expendlr.app.mapper.rowmappers.TransactionRowMapper;
import com.homs4j.expendlr.app.model.Transaction;
import com.homs4j.expendlr.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryTransactionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Transaction> save(Transaction transaction) {
        String SQL = "INSERT INTO transaction " +
                "VALUES(:id,:title,:type,:date,:amount,:accountId,:categoryId,:status)";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", transaction.getTransactionId());
        queryParams.put("title", transaction.getTitle());
        queryParams.put("type", transaction.getType().toString());
        queryParams.put("date", transaction.getDate());
        queryParams.put("amount", transaction.getAmount());
        queryParams.put("accountId", transaction.getAccount().getAccountId());
        queryParams.put("categoryId", transaction.getCategory().getCategoryId());
        queryParams.put("status", transaction.getStatus().toString());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(transaction) : Optional.empty();
    }

    @Override
    public Optional<Transaction> update(Transaction transaction) {
        String SQL = "UPDATE transaction SET " +
                "title = :title, amount = :amount, date = :date, type = :type " +
                "WHERE transaction_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("title", transaction.getTitle());
        queryParams.put("amount", transaction.getAmount());
        queryParams.put("date", transaction.getDate());
        queryParams.put("type",transaction.getType().toString());
        queryParams.put("id", transaction.getTransactionId());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(transaction) : Optional.empty();
    }

    @Override
    public List<Transaction> findAllByDate(Timestamp start, Timestamp end, String accountId) {
        String SQL = "SELECT * FROM transaction WHERE date BETWEEN :start AND :end " +
                "AND account_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("start", start);
        queryParams.put("end", end);
        queryParams.put("id", accountId);
        return jdbcTemplate.query(SQL, queryParams, new TransactionRowMapper());
    }

    @Override
    public Optional<Transaction> findById(String id) {
        String SQL = "SELECT * FROM transaction WHERE transaction_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        List<Transaction> result = jdbcTemplate.query(SQL, queryParams, new TransactionRowMapper());
        return result.stream().findFirst();
    }

    @Override
    public int deleteById(String id) {
        String SQL = "DELETE FROM transaction WHERE transaction_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id",id);
        return jdbcTemplate.update(SQL, queryParams);
    }

    @Override
    public int updateTransactionStatus(String id, TransactionStatus status) {
        String SQL = "UPDATE transaction SET status = :status WHERE transaction_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("status",status.toString());
        queryParams.put("id",id);
        return jdbcTemplate.update(SQL,queryParams);
    }
}
