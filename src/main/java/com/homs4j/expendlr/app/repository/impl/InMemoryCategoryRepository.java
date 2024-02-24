package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.model.Category;
import com.homs4j.expendlr.app.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryCategoryRepository implements CategoryRepository {
    @Override
    public Optional<Category> findById(String id) {
        String SQL = "SELECT * FROM category WHERE category_id = :id";
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        String SQL = "SELECT * FROM category";
        return null;
    }

    @Override
    public Optional<Category> save(Category category) {
        String SQL = "INSERT INTO user VALUES(:id,:name,:color)";
        return Optional.empty();
    }

    @Override
    public Optional<Category> update(Category category) {
        String SQL = "UPDATE category SET name = :name, color = :color " +
                "WHERE category_id = :id";
        return Optional.empty();
    }
}
