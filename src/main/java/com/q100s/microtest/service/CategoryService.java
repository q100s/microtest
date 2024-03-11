package com.q100s.microtest.service;

import com.q100s.microtest.dto.CategoryDto;
import com.q100s.microtest.dto.CreateOrUpdateCategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CreateOrUpdateCategoryDto newCategory);

    List<CategoryDto> getAll();

    CategoryDto getById(Long categoryId);

    CategoryDto updateCategory(Long categoryId, CreateOrUpdateCategoryDto newProperties);

    void deleteCategory(Long categoryId);
}