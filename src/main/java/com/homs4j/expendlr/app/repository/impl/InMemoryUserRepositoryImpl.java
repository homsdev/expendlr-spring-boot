package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.model.User;
import com.homs4j.expendlr.app.repository.UserRepository;
import com.homs4j.expendlr.app.mapper.rowmappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryUserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findById(String id) {
        String SQL = "SELECT * FROM app_user WHERE user_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        List<User> result = jdbcTemplate.query(SQL, queryParams, new UserRowMapper());
        return result.stream().findFirst();
    }

    @Override
    public Optional<User> save(User user) {
        String SQL = "INSERT INTO app_user VALUES(:id,:name,:lastName,:username,:email,:phone,:password)";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", user.getUserId());
        queryParams.put("name", user.getName());
        queryParams.put("lastName", user.getLastName());
        queryParams.put("username", user.getUsername());
        queryParams.put("email", user.getEmail());
        queryParams.put("phone", user.getPhoneNumber());
        queryParams.put("password", user.getPassword());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(user) : Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        String SQL = "UPDATE app_user " +
                "SET name = :name,last_name = :lastName, username = :username, email = :email, phone = :phone " +
                "WHERE user_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", user.getName());
        queryParams.put("lastName", user.getLastName());
        queryParams.put("username", user.getUsername());
        queryParams.put("email", user.getEmail());
        queryParams.put("phone", user.getPhoneNumber());
        queryParams.put("id", user.getUserId());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(user) : Optional.empty();
    }

    @Override
    public int delete(String id) {
        String SQL = "DELETE FROM app_user WHERE user_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        return jdbcTemplate.update(SQL, queryParams);
    }
}
