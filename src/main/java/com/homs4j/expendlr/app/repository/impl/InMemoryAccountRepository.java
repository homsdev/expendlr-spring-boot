package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.model.Account;
import com.homs4j.expendlr.app.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAccountRepository implements AccountRepository {
    @Override
    public Optional<Account> save(Account account) {
        String SQL = "INSERT INTO account " +
                "VALUES(:id,:alias,:balance,:created_on,:updated_on,:userId)";
        return Optional.empty();
    }

    @Override
    public Optional<Account> update(Account account) {
        String SQL = "UPDATE account SET alias = :alias";
        return Optional.empty();
    }

    @Override
    public List<Account> findAllByUserId(String userId) {
        String SQL = "SELECT * FROM account WHERE user_id = :id";
        return null;
    }

    @Override
    public Optional<Account> findById(String id) {
        String SQL = "DELETE * FROM account WHERE account_id = :id";
        return Optional.empty();
    }
}
