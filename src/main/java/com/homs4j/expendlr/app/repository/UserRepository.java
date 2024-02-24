package com.homs4j.expendlr.app.repository;

import com.homs4j.expendlr.app.model.User;

import java.util.Optional;

public interface UserRepository {
    public Optional<User> findById(String id);
    public Optional<User> save(User user);
    public Optional<User> update(User user);
    public int delete(String id);
}
