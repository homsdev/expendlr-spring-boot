package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.repository.AccountRepository;
import com.homs4j.expendlr.app.mapper.rowmappers.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryAccountRepository implements AccountRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryAccountRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Account> save(Account account) {
        String SQL = "INSERT INTO account " +
                "VALUES(:id,:alias,:balance,:createdOn,:updatedOn,:userId)";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", account.getAccountId());
        queryParams.put("alias", account.getAlias());
        queryParams.put("balance", account.getBalance());
        queryParams.put("createdOn", account.getCreatedOn());
        queryParams.put("updatedOn", account.getUpdatedOn());
        queryParams.put("userId", account.getUser().getUserId());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(account) : Optional.empty();
    }

    @Override
    public Optional<Account> update(Account account) {
        String SQL = "UPDATE account SET alias = :alias, balance = :balance " +
                "WHERE account_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("alias", account.getAlias());
        queryParams.put("balance", account.getBalance());
        queryParams.put("id",account.getAccountId());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(account) : Optional.empty();
    }

    @Override
    public List<Account> findAllByUserId(String userId) {
        String SQL = "SELECT * FROM account WHERE user_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", userId);
        return jdbcTemplate.query(SQL, queryParams, new AccountRowMapper());
    }

    @Override
    public Optional<Account> findById(String id) {
        String SQL = "SELECT * FROM account WHERE account_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        List<Account> result = jdbcTemplate.query(SQL, queryParams, new AccountRowMapper());
        return result.stream().findFirst();
    }
}
