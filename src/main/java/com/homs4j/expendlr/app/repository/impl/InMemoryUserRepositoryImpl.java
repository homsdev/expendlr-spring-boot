package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.model.User;
import com.homs4j.expendlr.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Optional;

public class InMemoryUserRepositoryImpl implements UserRepository {

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryUserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findById(String id) {
        String SQL = "SELECT * FROM user WHERE user_id= :id";
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User user) {
        String SQL = "INSERT INTO user VALUES(:id,:name,:lastName,:username,:email,:phone,:password)";
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        String SQL = "UPDATE user " +
                "SET name = :name,lastName = :lastName, username = :username, email = :email, phone = :phone " +
                "WHERE user_id = :id";
        return Optional.empty();
    }

    @Override
    public int delete(String id) {
        String SQL = "DELETE FROM user WHERE user_id = :id";
        return 0;
    }
}
