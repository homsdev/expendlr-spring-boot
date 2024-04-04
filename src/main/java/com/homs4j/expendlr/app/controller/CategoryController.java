package com.homs4j.expendlr.app.controller;


import com.homs4j.expendlr.app.dto.category.CategoryDTO;
import com.homs4j.expendlr.app.dto.category.PostCategory;
import com.homs4j.expendlr.app.dto.category.PutCategory;
import com.homs4j.expendlr.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") String id) {
        CategoryDTO result = categoryService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> result = categoryService.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> postCategory(@RequestBody PostCategory dto) {
        CategoryDTO result = categoryService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> putCategory(@PathVariable("id") String id, @RequestBody PutCategory dto) {
        CategoryDTO result = categoryService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
