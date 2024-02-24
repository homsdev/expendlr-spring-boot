package com.homs4j.expendlr.app.repository;

import com.homs4j.expendlr.app.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    public Optional<Account> save(Account account);
    public Optional<Account> update(Account account);
    List<Account> findAllByUserId(String userId);
    public Optional<Account> findById(String id);
}
