package com.homs4j.expendlr.app.repository;

import com.homs4j.expendlr.app.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    public Optional<Category> findById(String id);
    public List<Category> findAll();
    public Optional<Category> save(Category category);
    public Optional<Category> update(Category category);
}
