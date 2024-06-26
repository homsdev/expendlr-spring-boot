package com.homs4j.expendlr.app.service;

import com.homs4j.expendlr.app.dto.category.CategoryDTO;
import com.homs4j.expendlr.app.dto.category.PostCategory;
import com.homs4j.expendlr.app.dto.category.PutCategory;
import com.homs4j.expendlr.app.mapper.dtomapper.CategoryDTOMapper;
import com.homs4j.expendlr.app.model.Category;
import com.homs4j.expendlr.app.repository.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDTOMapper dtoMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryDTOMapper dtoMapper) {
        this.categoryRepository = categoryRepository;
        this.dtoMapper = dtoMapper;
    }

    public CategoryDTO findById(String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(dtoMapper::toDTO).orElse(null);//TODO: Implement Exception
    }

    public List<CategoryDTO> findAll() {
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(dtoMapper::toDTO).collect(Collectors.toList());
    }

    public CategoryDTO save(PostCategory dto) {
        Category category = dtoMapper.toCategory(dto);
        category.setCategoryId(UUID.randomUUID().toString());
        Optional<Category> saved = categoryRepository.save(category);
        return saved.map(dtoMapper::toDTO).orElse(null);//TODO: Implement Exception
    }

    public CategoryDTO update(PutCategory dto,String categoryId) {
        Category category = dtoMapper.toCategory(dto);
        category.setCategoryId(categoryId);
        Optional<Category> updated = categoryRepository.update(category);
        return updated.map(dtoMapper::toDTO).orElse(null);//TODO: Implement Exception
    }
}
