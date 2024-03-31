package com.homs4j.expendlr.app.mapper.dtomapper;

import com.homs4j.expendlr.app.dto.category.CategoryDTO;
import com.homs4j.expendlr.app.dto.category.PostCategory;
import com.homs4j.expendlr.app.dto.category.PutCategory;
import com.homs4j.expendlr.app.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOMapper {

    public Category toCategory(PostCategory dto) {
        return Category.builder()
                .title(dto.getTitle())
                .color(dto.getColor())
                .build();
    }

    public Category toCategory(PutCategory dto) {
        return Category.builder()
                .title(dto.getTitle())
                .color(dto.getColor())
                .build();
    }

    public Category toCategory(CategoryDTO dto) {
        return Category.builder()
                .categoryId(dto.getCategoryId())
                .title(dto.getName())
                .color(dto.getColor())
                .build();
    }

    public CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .name(category.getTitle())
                .color(category.getColor())
                .build();
    }

}
