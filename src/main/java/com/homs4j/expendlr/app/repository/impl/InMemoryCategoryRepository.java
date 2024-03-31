package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.model.Category;
import com.homs4j.expendlr.app.repository.CategoryRepository;
import com.homs4j.expendlr.app.mapper.rowmappers.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public InMemoryCategoryRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Category> findById(String id) {
        String SQL = "SELECT * FROM category WHERE category_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        List<Category> result = jdbcTemplate.query(SQL, queryParams, new CategoryRowMapper());
        return result.stream().findFirst();
    }

    @Override
    public List<Category> findAll() {
        String SQL = "SELECT * FROM category";
        return jdbcTemplate.query(SQL, new CategoryRowMapper());
    }

    @Override
    public Optional<Category> save(Category category) {
        String SQL = "INSERT INTO category VALUES(:id,:name,:color)";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", category.getCategoryId());
        queryParams.put("name", category.getTitle());
        queryParams.put("color", category.getColor());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(category) : Optional.empty();
    }

    @Override
    public Optional<Category> update(Category category) {
        String SQL = "UPDATE category SET name = :name, color = :color " +
                "WHERE category_id = :id";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("name", category.getTitle());
        queryParams.put("color", category.getColor());
        queryParams.put("id", category.getCategoryId());
        int result = jdbcTemplate.update(SQL, queryParams);
        return result > 0 ? Optional.of(category) : Optional.empty();
    }
}
