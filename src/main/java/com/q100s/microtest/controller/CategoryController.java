package com.q100s.microtest.controller;

import com.q100s.microtest.dto.CategoryDto;
import com.q100s.microtest.dto.CreateOrUpdateCategoryDto;
import com.q100s.microtest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateOrUpdateCategoryDto newCategory) {
        return ResponseEntity.ok(service.createCategory(newCategory));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(service.getById(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,
                                                      @RequestBody CreateOrUpdateCategoryDto newProperties) {
        return ResponseEntity.ok(service.updateCategory(categoryId, newProperties));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long categoryId) {
        service.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}