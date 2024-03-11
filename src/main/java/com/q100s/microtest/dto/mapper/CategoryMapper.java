package com.q100s.microtest.dto.mapper;

import com.q100s.microtest.dto.CategoryDto;
import com.q100s.microtest.dto.CreateOrUpdateCategoryDto;
import com.q100s.microtest.model.Category;
import com.q100s.microtest.model.Product;

public class CategoryMapper {
    public static Category mapIntoEntity(CreateOrUpdateCategoryDto dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        return entity;
    }

    public static CategoryDto mapIntoDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if (entity.getProducts() != null) {
            for (Product product : entity.getProducts()) {
                dto.getProducts().add(product.getId());
            }
        }
        return dto;
    }
}